package com.itiszakk.assetlab.desktop.util;

import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageUtils {

    private static StageService stageService;

    private static ControllerFactory controllerFactory;

    public static void init(ApplicationContext context, StageService stageService) {
        StageUtils.stageService = stageService;
        StageUtils.controllerFactory = new ControllerFactory(context, stageService);
    }

    public static void show(Class<? extends StageDefinition> stageClass) {
        stageService.show(stageClass, controllerFactory);
    }
}
