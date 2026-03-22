package com.itiszakk.assetlab.desktop.util;

import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;

public final class StageUtils {

    private static StageService stageService;
    private static ControllerFactory controllerFactory;

    private StageUtils() {}

    public static void init(ApplicationContext context, StageService stageService) {
        StageUtils.stageService = stageService;
        StageUtils.controllerFactory = new ControllerFactory(context, stageService);
    }

    public static void show(Class<? extends StageController> stageClass) {
        stageService.show(stageClass, controllerFactory);
    }
}
