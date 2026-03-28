package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.itiszakk.assetlab.desktop.configuration.DesktopModule;
import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.type.StageOptions;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.type.property.PropertyCategory;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsController implements StageController {

    private static final String STAGE_ID = "settings";
    private static final String STAGE_TITLE = DesktopModule.MODULE_ID + ".settings.title";
    private static final int STAGE_MIN_WIDTH = 800;
    private static final int STAGE_MIN_HEIGHT = 600;

    private static final Map<StageOptions, Object> STAGE_PROPERTIES = new EnumMap<>(StageOptions.class);
    static {
        STAGE_PROPERTIES.put(StageOptions.TITLE, TextUtils.getText(STAGE_TITLE));
        STAGE_PROPERTIES.put(StageOptions.MIN_WIDTH, STAGE_MIN_WIDTH);
        STAGE_PROPERTIES.put(StageOptions.MIN_HEIGHT, STAGE_MIN_HEIGHT);
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

    public SettingsController(ApplicationContext context) {
        propertyService = context.get(PropertyService.class);
    }

    @Override
    public String getId() {
        return STAGE_ID;
    }

    @Override
    public Map<StageOptions, Object> getOptions() {
        return STAGE_PROPERTIES;
    }

    @FXML
    @Override
    public void initialize() {

        Map<PropertyCategory, List<PropertyDefinition<?>>> propertiesByCategory =
                propertyService.getPropertiesByCategory();

        categoryItems.addAll(propertiesByCategory.keySet());
        categoriesView.setItems(categoryItems);
        categoriesView.getSelectionModel().selectedItemProperty()
                .addListener((observable, prev, next) -> showCategorySettings(next, propertiesByCategory.get(next)));
    }

    private void showCategorySettings(PropertyCategory category, List<PropertyDefinition<?>> properties) {

        categoryLabel.setText(category.getName());
        settingsContainer.getChildren().clear();

        for (PropertyDefinition<?> property : properties) {
            settingsContainer.getChildren().add(createPropertyContainer(property));
        }
    }

    private static HBox createPropertyContainer(PropertyDefinition<?> property) {

        HBox container = new HBox();
        container.getChildren().add(new Label(property.getName()));
        container.getChildren().add(new TextField(property.serialize()));

        return container;
    }
}
