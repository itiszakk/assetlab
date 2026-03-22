package com.itiszakk.assetlab.desktop.service.impl;

import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections4.MapUtils;

import com.itiszakk.assetlab.desktop.configuration.DesktopModule;
import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.desktop.type.StageOptions;
import com.itiszakk.assetlab.desktop.util.ControllerFactory;
import com.itiszakk.assetlab.desktop.util.StageUtils;
import com.itiszakk.assetlab.system.util.ResourceUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageServiceImpl implements StageService {

    private static final String DIRECTORY_DELIMITER = "/";
    private static final String FXML_DIRECTORY = "fxml";
    private static final String FXML_EXTENSION = ".fxml";

    private final Map<Class<? extends StageController>, StageController> stages = new HashMap<>();

    @Override
    public void register(StageController stage) {
        stages.put(stage.getClass(), stage);
    }

    @Override
    public void show(Class<? extends StageController> stageClass, ControllerFactory factory) {

        StageController stageDefinition = stages.get(stageClass);
        if (stageDefinition == null) {
            String message = MessageFormat.format("Stage for class [{0}] not found.", stageClass.getName());
            throw new IllegalStateException(message);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getStageResource(stageDefinition), getDesktopResourceBundle());
            loader.setControllerFactory(factory);

            Stage stage = buildStage(loader.load(), stageDefinition);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static URL getStageResource(StageController stageDefinition) {
        return ResourceUtils.load(buildStageResourcePath(stageDefinition));
    }

    private static String buildStageResourcePath(StageController stageDefinition) {
        return new StringBuilder()
                .append(FXML_DIRECTORY)
                .append(DIRECTORY_DELIMITER)
                .append(stageDefinition.getId())
                .append(FXML_EXTENSION)
                .toString();
    }

    private static ResourceBundle getDesktopResourceBundle() {
        return ResourceUtils.loadBundle(DesktopModule.TEXT_BUNDLE, StageUtils.class.getClassLoader());
    }

    private static Stage buildStage(Parent root, StageController stageDefinition) {

        Map<StageOptions, Object> properties = stageDefinition.getOptions();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle(MapUtils.getString(properties, StageOptions.TITLE));
        stage.setMinWidth(MapUtils.getDoubleValue(properties, StageOptions.MIN_WIDTH));
        stage.setMinHeight(MapUtils.getDoubleValue(properties, StageOptions.MIN_HEIGHT));
        stage.setMaximized(MapUtils.getBooleanValue(properties, StageOptions.MAXIMIZED));

        return stage;
    }
}
