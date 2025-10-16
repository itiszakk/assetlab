package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.type.StageProperty;
import com.itiszakk.assetlab.system.util.TextUtils;

public class AboutController implements StageDefinition {

    private static final String STAGE_ID = "about";

    private static final String STAGE_TITLE = "about.title";

    private static final int STAGE_MIN_WIDTH = 400;

    private static final int STAGE_MIN_HEIGHT = 300;

    private static final Map<StageProperty, Object> STAGE_PROPERTIES = new EnumMap<>(StageProperty.class);

    static {
        STAGE_PROPERTIES.put(StageProperty.TITLE, TextUtils.getText(STAGE_TITLE));
        STAGE_PROPERTIES.put(StageProperty.MIN_WIDTH, STAGE_MIN_WIDTH);
        STAGE_PROPERTIES.put(StageProperty.MIN_HEIGHT, STAGE_MIN_HEIGHT);
    }

    @Inject
    public AboutController() {

    }

    @Override
    public String getId() {
        return STAGE_ID;
    }

    @Override
    public Map<StageProperty, Object> getProperties() {
        return STAGE_PROPERTIES;
    }
}
