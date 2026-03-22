package com.itiszakk.assetlab.system.configuration;

import java.util.Collection;

import com.itiszakk.assetlab.system.service.EventService;
import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.service.impl.EventServiceImpl;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

public class SystemModule implements Module {

    public static final String MODULE_ID = "com.itiszakk.assetlab.system";
    private static final String TEXT_BUNDLE = "system_text";

    @Override
    public String getId() {
        return MODULE_ID;
    }

    @Override
    public Collection<PropertyDefinition<?>> getProperties() {
        return SystemProperties.PROPERTY_DEFINITIONS;
    }

    @Override
    public String getTextBundle() {
        return TEXT_BUNDLE;
    }

    @Override
    public void init(ApplicationContext context) {
        context.register(EventService.class, new EventServiceImpl());
        TextUtils.init(context.get(TextService.class));
    }
}
