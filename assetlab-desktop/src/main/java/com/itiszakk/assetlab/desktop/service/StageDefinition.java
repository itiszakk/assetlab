package com.itiszakk.assetlab.desktop.service;

import java.util.Map;

import com.itiszakk.assetlab.desktop.type.StageProperty;

public interface StageDefinition {

    String getId();

    Map<StageProperty, Object> getProperties();
}
