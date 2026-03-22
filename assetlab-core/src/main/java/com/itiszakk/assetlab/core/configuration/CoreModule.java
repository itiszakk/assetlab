package com.itiszakk.assetlab.core.configuration;

import java.util.List;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.service.TagService;
import com.itiszakk.assetlab.core.service.impl.AssetMetadataServiceImpl;
import com.itiszakk.assetlab.core.service.impl.AssetServiceImpl;
import com.itiszakk.assetlab.core.service.impl.TagServiceImpl;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.configuration.Module;
import com.itiszakk.assetlab.system.configuration.SystemModule;

public class CoreModule implements Module {

    public static final String MODULE_ID = "com.itiszakk.assetlab.core";

    private static final List<String> DEPENDENCIES = List.of(SystemModule.MODULE_ID);

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public List<String> getDependencies() {
        return DEPENDENCIES;
    }

    @Override
    public void init(ApplicationContext context) {
        context.register(AssetService.class, new AssetServiceImpl());
        context.register(AssetMetadataService.class, new AssetMetadataServiceImpl());
        context.register(TagService.class, new TagServiceImpl());
    }
}
