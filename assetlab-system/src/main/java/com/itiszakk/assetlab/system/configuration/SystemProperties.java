package com.itiszakk.assetlab.system.configuration;

import java.util.List;
import java.util.Locale;

import com.itiszakk.assetlab.system.type.property.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

public final class SystemProperties {

    private static final String LOCALE_PROPERTY_ID = SystemModule.MODULE_ID + ".property.locale";
    private static final String LOCALE_PROPERTY_DESCRIPTION = SystemModule.MODULE_ID + ".property.locale.description";

    private SystemProperties() {}

    public static final PropertyDefinition<Locale> LOCALE = PropertyDefinition.builder(Locale.class)
            .id(LOCALE_PROPERTY_ID)
            .name(() -> TextUtils.getText(LOCALE_PROPERTY_ID))
            .description(() -> TextUtils.getText(LOCALE_PROPERTY_DESCRIPTION))
            .category(SystemPropertyCategories.GENERAL)
            .defaultValue(Locale.forLanguageTag("en"))
            .serializer(Locale::getLanguage)
            .deserializer(Locale::forLanguageTag)
            .build();

    public static final List<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(LOCALE);
}
