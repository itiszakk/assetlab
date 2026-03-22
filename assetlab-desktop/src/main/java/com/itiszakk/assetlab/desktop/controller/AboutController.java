package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.Map;

import com.itiszakk.assetlab.desktop.service.StageDefinition;
import com.itiszakk.assetlab.desktop.type.StageOptions;
import com.itiszakk.assetlab.system.BuildInfo;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AboutController implements StageDefinition {

    private static final String BUILD_TITLE = "about.build.title";

    private static final String BUILD_TIMESTAMP = "about.build.timestamp";

    private static final String STAGE_ID = "about";

    private static final String STAGE_TITLE = "about.title";

    private static final int STAGE_MIN_WIDTH = 400;

    private static final int STAGE_MIN_HEIGHT = 300;

    private static final Map<StageOptions, Object> STAGE_PROPERTIES = new EnumMap<>(StageOptions.class);

    static {
        STAGE_PROPERTIES.put(StageOptions.TITLE, TextUtils.getText(STAGE_TITLE));
        STAGE_PROPERTIES.put(StageOptions.MIN_WIDTH, STAGE_MIN_WIDTH);
        STAGE_PROPERTIES.put(StageOptions.MIN_HEIGHT, STAGE_MIN_HEIGHT);
    }

    @FXML
    private Label buildTitle;

    @FXML
    private Label buildTimestamp;

    @FXML
    public void initialize() {
        buildTitle.setText(TextUtils.getText(BUILD_TITLE, BuildInfo.VERSION));
        buildTimestamp.setText(TextUtils.getText(BUILD_TIMESTAMP, BuildInfo.TIMESTAMP));
    }

    @Override
    public String getId() {
        return STAGE_ID;
    }

    @Override
    public Map<StageOptions, Object> getProperties() {
        return STAGE_PROPERTIES;
    }
}
