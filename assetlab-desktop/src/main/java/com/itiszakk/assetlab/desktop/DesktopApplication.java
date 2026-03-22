package com.itiszakk.assetlab.desktop;

import com.itiszakk.assetlab.core.configuration.CoreModule;
import com.itiszakk.assetlab.desktop.configuration.DesktopModule;
import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.desktop.util.StageUtils;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.configuration.ModuleManager;
import com.itiszakk.assetlab.system.configuration.SystemModule;

import javafx.application.Application;
import javafx.stage.Stage;

public class DesktopApplication extends Application {

    @Override
    public void init() {

        ApplicationContext context = new ApplicationContext();

        ModuleManager moduleManager = new ModuleManager(context);
        moduleManager.register(new SystemModule());
        moduleManager.register(new CoreModule());
        moduleManager.register(new DesktopModule());

        moduleManager.start();
    }

    @Override
    public void start(Stage stage) {
        StageUtils.show(MainController.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
