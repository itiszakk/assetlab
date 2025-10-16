package com.itiszakk.assetlab.desktop.service;

import com.itiszakk.assetlab.desktop.util.ControllerFactory;

public interface StageService {

    void register(StageDefinition stage);

    void show(Class<? extends StageDefinition> stageClass, ControllerFactory controllerFactory);
}
