package com.itiszakk.assetlab.desktop.configuration;

import java.util.Collection;
import java.util.List;

import com.itiszakk.assetlab.core.configuration.CoreModule;
import com.itiszakk.assetlab.desktop.service.StageService;
import com.itiszakk.assetlab.desktop.service.impl.StageServiceImpl;
import com.itiszakk.assetlab.desktop.util.StageUtils;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.configuration.Module;
import com.itiszakk.assetlab.system.configuration.SystemModule;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;

public class DesktopModule implements Module {

    public static final String MODULE_ID = "com.itiszakk.assetlab.desktop";
    public static final String TEXT_BUNDLE = "desktop_text";

    private static final List<String> DEPENDENCIES = List.of(
            SystemModule.MODULE_ID,
            CoreModule.MODULE_ID
    );

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public List<String> getDependencies() {
        return DEPENDENCIES;
    }

    @Override
    public Collection<PropertyDefinition<?>> getProperties() {
        return DesktopProperties.PROPERTY_DEFINITIONS;
    }

    @Override
    public String getTextBundle() {
        return TEXT_BUNDLE;
    }

    @Override
    public void init(ApplicationContext context) {

        StageService stageService = new StageServiceImpl();
        context.register(StageService.class, stageService);

        StageUtils.init(context, stageService);
    }
}
