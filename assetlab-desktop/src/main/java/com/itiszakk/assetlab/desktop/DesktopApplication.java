package com.itiszakk.assetlab.desktop;

import com.itiszakk.assetlab.desktop.configuration.ApplicationComponent;
import com.itiszakk.assetlab.desktop.configuration.DaggerApplicationComponent;
import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.desktop.util.StageUtils;

import javafx.application.Application;
import javafx.stage.Stage;

public class DesktopApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void init() {
        applicationComponent = DaggerApplicationComponent.create();
        applicationComponent.getSystemInitializer().initialize();
    }

    @Override
    public void start(Stage stage) {
        StageUtils.init(applicationComponent);
        StageUtils.show(MainController.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
