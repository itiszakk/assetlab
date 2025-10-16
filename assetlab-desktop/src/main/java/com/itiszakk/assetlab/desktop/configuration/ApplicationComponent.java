package com.itiszakk.assetlab.desktop.configuration;

import javax.inject.Singleton;

import com.itiszakk.assetlab.core.configuration.CoreModule;
import com.itiszakk.assetlab.desktop.controller.AboutController;
import com.itiszakk.assetlab.desktop.controller.AssetsController;
import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.desktop.controller.MetadataController;
import com.itiszakk.assetlab.desktop.controller.PreviewController;
import com.itiszakk.assetlab.desktop.controller.SettingsController;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.system.configuration.SystemModule;
import com.itiszakk.assetlab.system.service.impl.SystemInitializer;

import dagger.Component;

@Singleton
@Component(modules = {SystemModule.class, CoreModule.class, DesktopModule.class})
public interface ApplicationComponent {

    SystemInitializer getSystemInitializer();

    StageService getStageService();

    MainController getMainController();

    AssetsController getAssetsController();

    PreviewController getPreviewController();

    MetadataController getMetadataController();

    SettingsController getSettingsController();

    AboutController getAboutController();
}
