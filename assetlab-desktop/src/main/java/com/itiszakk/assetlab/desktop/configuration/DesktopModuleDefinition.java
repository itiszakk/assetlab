package com.itiszakk.assetlab.desktop.configuration;

import java.util.Collection;

import javax.inject.Inject;

import com.itiszakk.assetlab.desktop.type.DesktopProperties;
import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.type.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

public class DesktopModuleDefinition implements ModuleDefinition {

    public static final String MODULE_ID = "com.itiszakk.assetlab.desktop";

    public static final String TEXT_BUNDLE = "desktop_text";

    private final TextService textService;

    @Inject
    public DesktopModuleDefinition(TextService textService) {
        this.textService = textService;
    }

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public int getInitOrder() {
        return 2;
    }

    @Override
    public void start() {
        TextUtils.init(textService);
    }

    @Override
    public Collection<PropertyDefinition<?>> getProperties() {
        return DesktopProperties.PROPERTY_DEFINITIONS;
    }

    @Override
    public String getTextBundle() {
        return TEXT_BUNDLE;
    }
}
