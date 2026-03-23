package com.itiszakk.assetlab.desktop.controller;

import java.util.EnumMap;
import java.util.Map;

import com.itiszakk.assetlab.desktop.configuration.DesktopModule;
import com.itiszakk.assetlab.desktop.type.StageController;
import com.itiszakk.assetlab.desktop.type.StageOptions;
import com.itiszakk.assetlab.system.BuildInfo;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AboutController implements StageController {

    private static final String CONTROLLER_ID = "about";

    private static final String STAGE_TITLE = DesktopModule.MODULE_ID + ".about.title";
    private static final String BUILD_TITLE = DesktopModule.MODULE_ID + ".about.build.title";
    private static final String BUILD_TIMESTAMP = DesktopModule.MODULE_ID + ".about.build.timestamp";

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

    @Override
    public String getId() {
        return CONTROLLER_ID;
    }

    @Override
    public Map<StageOptions, Object> getOptions() {
        return STAGE_PROPERTIES;
    }

    @FXML
    @Override
    public void initialize() {
        buildTitle.setText(TextUtils.getText(BUILD_TITLE, BuildInfo.VERSION));
        buildTimestamp.setText(TextUtils.getText(BUILD_TIMESTAMP, BuildInfo.TIMESTAMP));
    }
}
