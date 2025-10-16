package com.itiszakk.assetlab.desktop.configuration;

import javax.inject.Singleton;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.service.TagService;
import com.itiszakk.assetlab.desktop.controller.AboutController;
import com.itiszakk.assetlab.desktop.controller.AssetsController;
import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.desktop.controller.MetadataController;
import com.itiszakk.assetlab.desktop.controller.PreviewController;
import com.itiszakk.assetlab.desktop.controller.SettingsController;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.desktop.service.impl.StageServiceImpl;
import com.itiszakk.assetlab.system.service.ModuleDefinition;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface DesktopModule {

    @Binds
    @IntoMap
    @StringKey(DesktopModuleDefinition.MODULE_ID)
    ModuleDefinition bindDesktopModuleDefinition(DesktopModuleDefinition moduleDefinition);

    @Binds
    @Singleton
    StageService bindStageService(StageServiceImpl impl);

    @Provides
    @Singleton
    static MainController provideMainController(StageService stageService,
                                                AssetService assetService,
                                                AssetMetadataService assetMetadataService) {
        MainController controller = new MainController(assetService, assetMetadataService);
        stageService.register(controller);
        return controller;
    }

    @Provides
    @Singleton
    static AssetsController provideAssetController(AssetService assetService,
                                                   AssetMetadataService assetMetadataService) {
        return new AssetsController(assetService, assetMetadataService);
    }

    @Provides
    @Singleton
    static PreviewController providePreviewController(AssetsController assetsController,
                                                      AssetService assetService) {
        PreviewController controller = new PreviewController(assetService);
        assetsController.register(controller);
        return controller;
    }

    @Provides
    @Singleton
    static MetadataController provideMetadataController(AssetsController assetsController,
                                                        AssetService assetService,
                                                        AssetMetadataService assetMetadataService,
                                                        TagService tagService) {
        MetadataController controller = new MetadataController(assetService, assetMetadataService, tagService);
        assetsController.register(controller);
        return controller;
    }

    @Provides
    @Singleton
    static SettingsController provideSettingsController(StageService stageService) {
        SettingsController controller = new SettingsController();
        stageService.register(controller);
        return controller;
    }

    @Provides
    @Singleton
    static AboutController provideAboutController(StageService stageService) {
        AboutController controller = new AboutController();
        stageService.register(controller);
        return controller;
    }
}
