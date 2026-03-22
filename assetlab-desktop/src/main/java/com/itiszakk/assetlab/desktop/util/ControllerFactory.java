package com.itiszakk.assetlab.desktop.util;

import java.util.HashMap;
import java.util.Map;

import com.itiszakk.assetlab.desktop.controller.AboutController;
import com.itiszakk.assetlab.desktop.controller.AssetsController;
import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.desktop.controller.MetadataController;
import com.itiszakk.assetlab.desktop.controller.PreviewController;
import com.itiszakk.assetlab.desktop.controller.SettingsController;
import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final StageService stageService;
    private final Map<Class<?>, Object> controllers = new HashMap<>();

    public ControllerFactory(ApplicationContext context, StageService stageService) {

        this.stageService = stageService;

        registerStage(new MainController(context));
        registerStage(new SettingsController(context));
        registerStage(new AboutController());

        registerController(new AssetsController(context));
        registerController(new PreviewController(context));
        registerController(new MetadataController(context));
    }

    private void registerController(Object controller) {
        controllers.put(controller.getClass(), controller);
    }

    private void registerStage(StageController stage) {
        registerController(stage);
        stageService.register(stage);
    }

    @Override
    public Object call(Class<?> type) {
        return controllers.get(type);
    }
}
