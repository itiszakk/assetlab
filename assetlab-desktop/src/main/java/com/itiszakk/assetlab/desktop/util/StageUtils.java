package com.itiszakk.assetlab.desktop.util;

import com.itiszakk.assetlab.desktop.configuration.ApplicationComponent;
import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.service.StageService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageUtils {

    private static StageService stageService;

    private static ControllerFactory controllerFactory;

    public static void init(ApplicationComponent applicationComponent) {
        StageUtils.stageService = applicationComponent.getStageService();
        StageUtils.controllerFactory = new ControllerFactory(applicationComponent);
    }

    public static void show(Class<? extends StageDefinition> stageClass) {
        stageService.show(stageClass, controllerFactory);
    }
}
