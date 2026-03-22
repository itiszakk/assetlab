package com.itiszakk.assetlab.desktop.service;

import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.util.ControllerFactory;

public interface StageService {

    void register(StageController stage);

    void show(Class<? extends StageController> stageClass, ControllerFactory controllerFactory);
}
