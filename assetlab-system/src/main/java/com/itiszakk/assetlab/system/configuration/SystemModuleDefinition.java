package com.itiszakk.assetlab.system.configuration;

import java.util.Collection;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.type.PropertyDefinition;
import com.itiszakk.assetlab.system.type.SystemProperties;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class SystemModuleDefinition implements ModuleDefinition {

    public static final String MODULE_ID = "com.itiszakk.assetlab.system";

    private static final String TEXT_BUNDLE = "system_text";

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public int getInitOrder() {
        return 0;
    }

    @Override
    public Collection<PropertyDefinition<?>> getProperties() {
        return SystemProperties.PROPERTY_DEFINITIONS;
    }

    @Override
    public String getTextBundle() {
        return TEXT_BUNDLE;
    }
}
