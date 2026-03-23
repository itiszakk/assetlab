package com.itiszakk.assetlab.desktop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.desktop.type.AssetItem;
import com.itiszakk.assetlab.desktop.type.Controller;
import com.itiszakk.assetlab.desktop.configuration.DesktopEvents;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.service.EventService;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PreviewController implements Controller {

    private static final String CONTROLLER_ID = "preview";

    private final AssetService assetService;

    @FXML
    private StackPane previewContainer;

    @FXML
    private ScrollPane previewScroll;

    @FXML
    private ImageView preview;

    public PreviewController(ApplicationContext context) {

        assetService = context.get(AssetService.class);

        EventService eventService = context.get(EventService.class);
        eventService.subscribe(DesktopEvents.ASSET_ITEM_SELECTED, this::onItemSelected);
        eventService.subscribe(DesktopEvents.ASSET_ITEM_DESELECTED, this::onItemDeselected);
    }

    @Override
    public String getId() {
        return CONTROLLER_ID;
    }

    @FXML
    @Override
    public void initialize() {
        previewContainer.disableProperty().bind(preview.imageProperty().isNull());
    }

    private void onItemSelected(AssetItem item) {
        try {
            Asset asset = assetService.load(item.getAssetId());

            File file = new File(asset.getPath());
            InputStream input = new FileInputStream(file);

            Image image = new Image(input);
            preview.setImage(image);

            fitPreview();

        } catch (Exception e) {
            preview.setImage(null);
        }
    }

    private void fitPreview() {
        preview.setFitWidth(previewScroll.getWidth());
        preview.setFitHeight(previewScroll.getHeight());
    }

    private void onItemDeselected() {
        preview.setImage(null);
    }
}
