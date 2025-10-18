package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.type.StageProperty;
import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.type.PropertyCategory;
import com.itiszakk.assetlab.system.type.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

    private final PropertyService propertyService;

    private final ObservableList<PropertyCategory> categoryItems = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;

    @FXML
    private ListView<PropertyCategory> categoriesView;

    @FXML
    private Label categoryLabel;

    @FXML
    private VBox settingsContainer;

    @Inject
    public SettingsController(PropertyService propertyService) {
        this.propertyService = propertyService;
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

        Map<PropertyCategory, List<PropertyDefinition<?>>> propertiesByCategory =
                propertyService.getPropertiesByCategory();

        categoryItems.addAll(propertiesByCategory.keySet());
        categoriesView.setItems(categoryItems);
        categoriesView.getSelectionModel().selectedItemProperty()
                .addListener((observable, prev, next) -> showCategorySettings(next, propertiesByCategory.get(next)));
    }

    private void showCategorySettings(PropertyCategory category, List<PropertyDefinition<?>> properties) {

        categoryLabel.setText(category.getName().get());
        settingsContainer.getChildren().clear();

        for (PropertyDefinition<?> property : properties) {
            settingsContainer.getChildren().add(createPropertyContainer(property));
        }
    }

    private static HBox createPropertyContainer(PropertyDefinition<?> property) {

        HBox container = new HBox();
        container.getChildren().add(new Label(property.getName().get()));
        container.getChildren().add(new TextField(property.serialize()));

        return container;
    }
}
