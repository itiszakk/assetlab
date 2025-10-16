package com.itiszakk.assetlab.desktop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.desktop.controller.listener.AssetSelectionListener;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PreviewController implements AssetSelectionListener {

    private final AssetService assetService;

    @FXML
    private StackPane previewContainer;

    @FXML
    private ScrollPane previewScroll;

    @FXML
    private ImageView preview;

    @Inject
    public PreviewController(AssetService assetService) {
        this.assetService = assetService;
    }

    @FXML
    private void initialize() {
        previewContainer.disableProperty().bind(preview.imageProperty().isNull());
    }

    @Override
    public void onAssetSelected(String id) {
        try {
            Asset asset = assetService.load(id);

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

    @Override
    public void onClearSelection() {
        preview.setImage(null);
    }
}
