package com.itiszakk.assetlab.desktop.service;

import java.util.Map;

import com.itiszakk.assetlab.desktop.type.StageOptions;

public interface StageDefinition {

    String getId();

    Map<StageOptions, Object> getProperties();
}
