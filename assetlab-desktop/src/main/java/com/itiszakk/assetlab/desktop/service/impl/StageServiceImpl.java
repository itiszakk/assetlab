package com.itiszakk.assetlab.desktop.service.impl;

import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.collections4.MapUtils;

import com.itiszakk.assetlab.desktop.configuration.DesktopModuleDefinition;
import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.desktop.type.StageProperty;
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

    private final Map<Class<? extends StageDefinition>, StageDefinition> stages = new HashMap<>();

    @Inject
    public StageServiceImpl() {

    }

    @Override
    public void register(StageDefinition stage) {
        stages.put(stage.getClass(), stage);
    }

    @Override
    public void show(Class<? extends StageDefinition> stageClass, ControllerFactory controllerFactory) {

        StageDefinition stageDefinition = stages.get(stageClass);
        if (stageDefinition == null) {
            String message = MessageFormat.format("Stage for class [{0}] not found.", stageClass.getName());
            throw new IllegalStateException(message);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getStageResource(stageDefinition), getDesktopResourceBundle());
            loader.setControllerFactory(controllerFactory);

            Stage stage = buildStage(loader.load(), stageDefinition);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static URL getStageResource(StageDefinition stageDefinition) {
        return ResourceUtils.load(buildStageResourcePath(stageDefinition));
    }

    private static String buildStageResourcePath(StageDefinition stageDefinition) {
        return new StringBuilder()
                .append(FXML_DIRECTORY)
                .append(DIRECTORY_DELIMITER)
                .append(stageDefinition.getId())
                .append(FXML_EXTENSION)
                .toString();
    }

    private static ResourceBundle getDesktopResourceBundle() {
        return ResourceUtils.loadBundle(DesktopModuleDefinition.TEXT_BUNDLE, StageUtils.class.getClassLoader());
    }

    private static Stage buildStage(Parent root, StageDefinition stageDefinition) {

        Map<StageProperty, Object> properties = stageDefinition.getProperties();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle(MapUtils.getString(properties, StageProperty.TITLE));
        stage.setMinWidth(MapUtils.getDoubleValue(properties, StageProperty.MIN_WIDTH));
        stage.setMinHeight(MapUtils.getDoubleValue(properties, StageProperty.MIN_HEIGHT));
        stage.setMaximized(MapUtils.getBooleanValue(properties, StageProperty.MAXIMIZED));

        return stage;
    }
}