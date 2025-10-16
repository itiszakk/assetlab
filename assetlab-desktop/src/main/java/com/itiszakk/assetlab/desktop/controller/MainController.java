package com.itiszakk.assetlab.desktop.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.core.type.Asset.AssetBuilder;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.type.StageProperty;
import com.itiszakk.assetlab.desktop.util.FileUtils;
import com.itiszakk.assetlab.desktop.util.StageUtils;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController implements StageDefinition {

    private static final String STAGE_ID = "main";

    private static final String STAGE_TITLE = "main.title";

    private static final int STAGE_MIN_WIDTH = 800;

    private static final int STAGE_MIN_HEIGHT = 600;

    private static final Map<StageProperty, Object> STAGE_PROPERTIES = new EnumMap<>(StageProperty.class);

    static {
        STAGE_PROPERTIES.put(StageProperty.TITLE, TextUtils.getText(STAGE_TITLE));
        STAGE_PROPERTIES.put(StageProperty.MIN_WIDTH, STAGE_MIN_WIDTH);
        STAGE_PROPERTIES.put(StageProperty.MIN_HEIGHT, STAGE_MIN_HEIGHT);
        STAGE_PROPERTIES.put(StageProperty.MAXIMIZED, true);
    }

    private final AssetService assetService;

    private final AssetMetadataService assetMetadataService;

    @FXML
    private MenuItem settingsMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @Inject
    public MainController(AssetService assetService, AssetMetadataService assetMetadataService) {
        this.assetService = assetService;
        this.assetMetadataService = assetMetadataService;
    }

    @Override
    public String getId() {
        return STAGE_ID;
    }

    @Override
    public Map<StageProperty, Object> getProperties() {
        return STAGE_PROPERTIES;
    }

    @FXML
    private void initialize() {
        settingsMenuItem.setOnAction(event -> StageUtils.show(SettingsController.class));
        aboutMenuItem.setOnAction(event -> StageUtils.show(AboutController.class));
    }

    @FXML
    private void loadAssets() {

        LoadTask task = new LoadTask();
        task.setOnFailed(event -> throwTaskException(task));

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private static void throwTaskException(Task<?> task) {
        Throwable exception = task.getException();
        if (exception != null) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    private class LoadTask extends Task<Void> {

        private final List<File> files;

        private final LocalDateTime timestamp;

        public LoadTask() {
            this.files = FileUtils.load();
            this.timestamp = LocalDateTime.now();
        }

        @Override
        protected Void call() {

            if (CollectionUtils.isEmpty(files)) {
                return null;
            }

            List<Asset> assets = new ArrayList<>();
            List<AssetMetadata> assetsMetadata = new ArrayList<>();

            for (File file : files) {
                String id = UUID.randomUUID().toString();
                assets.add(createAsset(id, file));
                assetsMetadata.add(createAssetMetadata(id, file));
            }

            assetService.saveAll(assets);
            assetMetadataService.saveAll(assetsMetadata);

            return null;
        }

        private Asset createAsset(String id, File file) {

            Pair<String, String> split = FileUtils.splitFileName(file.getName());
            String filename = split.getLeft();
            String extension = split.getRight();

            AssetBuilder builder = Asset.builder()
                    .id(id)
                    .name(filename)
                    .extension(extension)
                    .path(file.getPath())
                    .size(file.length());

            if (FileUtils.isImageExtension(extension)) {
                BufferedImage image = FileUtils.toImage(file);
                if (image != null) {
                    builder.width(image.getWidth());
                    builder.height(image.getHeight());
                }
            }

            return builder.build();
        }

        private AssetMetadata createAssetMetadata(String id, File file) {
            return AssetMetadata.builder()
                    .assetId(id)
                    .assetDisplayName(file.getName())
                    .timestamp(timestamp)
                    .build();
        }
    }
}
