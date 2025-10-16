package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.type.StageProperty;
import com.itiszakk.assetlab.desktop.type.item.LocalizedItem;
import com.itiszakk.assetlab.desktop.type.settings.GeneralSettingsCategory;
import com.itiszakk.assetlab.desktop.type.settings.SettingsCategory;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SettingsController implements StageDefinition {

    private static final String STAGE_ID = "settings";

    private static final String STAGE_TITLE = "settings.title";

    private static final int STAGE_MIN_WIDTH = 800;

    private static final int STAGE_MIN_HEIGHT = 600;

    private static final Map<StageProperty, Object> STAGE_PROPERTIES = new EnumMap<>(StageProperty.class);

    static {
        STAGE_PROPERTIES.put(StageProperty.TITLE, TextUtils.getText(STAGE_TITLE));
        STAGE_PROPERTIES.put(StageProperty.MIN_WIDTH, STAGE_MIN_WIDTH);
        STAGE_PROPERTIES.put(StageProperty.MIN_HEIGHT, STAGE_MIN_HEIGHT);
    }

    private static final List<SettingsCategory> CATEGORIES = List.of(
            new GeneralSettingsCategory()
    );

    private final ObservableList<LocalizedItem> categoryItems = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;

    @FXML
    private ListView<LocalizedItem> categoriesView;

    @FXML
    private Label categoryLabel;

    @FXML
    private VBox settingsContainer;

    @Inject
    public SettingsController() {

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

        Map<String, SettingsCategory> categoryById = HashMap.newHashMap(CATEGORIES.size());
        CATEGORIES.forEach(category -> {
            categoryById.put(category.getId(), category);
            categoryItems.add(LocalizedItem.of(category.getId()));
        });

        categoryItems.addAll();
        categoriesView.setItems(categoryItems);
        categoriesView.getSelectionModel().selectedItemProperty()
                .addListener((observable, prev, next) -> {
                    String categoryId = next.getId();
                    showCategorySettings(categoryById.get(categoryId));
                });
    }

    private void showCategorySettings(SettingsCategory category) {
        categoryLabel.setText(TextUtils.getText(category.getId()));
    }
}
