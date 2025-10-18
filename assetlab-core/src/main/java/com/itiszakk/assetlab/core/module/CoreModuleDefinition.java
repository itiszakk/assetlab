package com.itiszakk.assetlab.core.module;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.ModuleDefinition;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class CoreModuleDefinition implements ModuleDefinition {

    public static final String MODULE_ID = "com.itiszakk.assetlab.core";

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public int getInitOrder() {
        return 1;
    }

    @Override
    public String getTextBundle() {
        return null;
    }
}
