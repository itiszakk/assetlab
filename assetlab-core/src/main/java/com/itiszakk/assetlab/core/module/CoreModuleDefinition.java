package com.itiszakk.assetlab.core.module;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.ModuleDefinition;

public class CoreModuleDefinition implements ModuleDefinition {

    public static final String MODULE_ID = "com.itiszakk.assetlab.core";

    @Inject
    public CoreModuleDefinition() {

    }

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public int getInitOrder() {
        return 1;
    }
}
