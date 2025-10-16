package com.itiszakk.assetlab.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.service.LifecycleListener;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.desktop.controller.listener.AssetSelectionListener;
import com.itiszakk.assetlab.desktop.type.item.AssetItem;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

public class AssetsController implements LifecycleListener<AssetMetadata> {

    private static final String CONTEXT_MENU_ITEM_DELETE = "assets.context_menu.item.delete";

    private final AssetService assetService;

    private final AssetMetadataService assetMetadataService;

    private final ObservableList<AssetItem> assetItems = FXCollections.observableArrayList();

    private final List<AssetSelectionListener> selectionListeners = new ArrayList<>();

    @FXML
    private Label countLabel;

    @FXML
    private ListView<AssetItem> assetsView;

    @Inject
    public AssetsController(AssetService assetService, AssetMetadataService assetMetadataService) {
        this.assetService = assetService;
        this.assetMetadataService = assetMetadataService;
        assetMetadataService.register(this);
    }

    public void register(AssetSelectionListener listener) {
        selectionListeners.add(listener);
    }

    @FXML
    private void initialize() {

        countLabel.textProperty().bind(Bindings.concat(" (", Bindings.size(assetItems), ")"));

        assetsView.setItems(assetItems);
        assetsView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        assetsView.getSelectionModel().selectedItemProperty()
                .addListener((observable, prev, next) -> notifySelectionListeners(next));

        initContextMenu();
    }

    private void initContextMenu() {

        MenuItem deleteItem = new MenuItem(TextUtils.getText(CONTEXT_MENU_ITEM_DELETE));
        deleteItem.setOnAction(event -> {

            List<String> ids = assetsView.getSelectionModel().getSelectedItems().stream()
                    .map(AssetItem::getAssetId)
                    .toList();

            assetService.deleteAll(ids);
            assetMetadataService.deleteAll(ids);
        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(deleteItem);

        assetsView.setContextMenu(contextMenu);
    }

    private void notifySelectionListeners(AssetItem item) {
        if (item == null) {
            selectionListeners.forEach(AssetSelectionListener::onClearSelection);
        } else {
            selectionListeners.forEach(listener -> listener.onAssetSelected(item.getAssetId()));
        }
    }

    @Override
    public void afterSaveAll(List<AssetMetadata> metadata) {
        Platform.runLater(() -> {

            List<AssetItem> items = metadata.stream()
                    .map(AssetsController::createAssetItem)
                    .toList();

            assetItems.addAll(items);
            assetsView.getSelectionModel().select(items.getLast());
        });
    }

    @Override
    public void afterDelete(AssetMetadata metadata) {
        Platform.runLater(() -> assetItems.remove(createAssetItem(metadata)));
    }

    @Override
    public void afterDeleteAll(List<AssetMetadata> metadata) {
        Platform.runLater(() -> {

            List<AssetItem> items = metadata.stream()
                    .map(AssetsController::createAssetItem)
                    .toList();

            assetItems.removeAll(items);
        });
    }

    private static AssetItem createAssetItem(AssetMetadata metadata) {
        return AssetItem.builder()
                .assetId(metadata.getAssetId())
                .assetDisplayName(metadata.getAssetDisplayName())
                .build();
    }
}
