package com.itiszakk.assetlab.desktop.controller;

import java.util.Collection;
import java.util.List;

import com.itiszakk.assetlab.core.configuration.CoreEvents;
import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.desktop.configuration.DesktopEvents;
import com.itiszakk.assetlab.desktop.configuration.DesktopModule;
import com.itiszakk.assetlab.desktop.type.AssetItem;
import com.itiszakk.assetlab.desktop.type.Controller;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.service.EventService;
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

public class AssetsController implements Controller {

    private static final String CONTROLLER_ID = "assets";

    private static final String CONTEXT_MENU_ITEM_DELETE = DesktopModule.MODULE_ID + ".assets.context.menu.item.delete";

    private final EventService eventService;
    private final AssetService assetService;
    private final AssetMetadataService assetMetadataService;
    private final ObservableList<AssetItem> assetItems = FXCollections.observableArrayList();

    @FXML
    private Label countLabel;

    @FXML
    private ListView<AssetItem> assetsView;

    public AssetsController(ApplicationContext context) {
        eventService = context.get(EventService.class);
        assetService = context.get(AssetService.class);
        assetMetadataService = context.get(AssetMetadataService.class);

        eventService.subscribe(CoreEvents.ASSETS_METADATA_SAVED, this::onMetadataSaved);
        eventService.subscribe(CoreEvents.ASSETS_METADATA_DELETED, this::onMetadataDeleted);
    }

    @Override
    public String getId() {
        return CONTROLLER_ID;
    }

    @FXML
    @Override
    public void initialize() {

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
            eventService.send(DesktopEvents.ASSET_ITEM_DESELECTED);
        } else {
            eventService.send(DesktopEvents.ASSET_ITEM_SELECTED, item);
        }
    }

    public void onMetadataSaved(Collection<AssetMetadata> metadata) {
        Platform.runLater(() -> {

            List<AssetItem> items = metadata.stream()
                    .map(AssetsController::createAssetItem)
                    .toList();

            assetItems.addAll(items);
            assetsView.getSelectionModel().select(items.getLast());
        });
    }

    public void onMetadataDeleted(Collection<AssetMetadata> metadata) {
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
