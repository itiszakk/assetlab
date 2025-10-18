package com.itiszakk.assetlab.system.type;

import java.util.List;
import java.util.Locale;

import com.itiszakk.assetlab.system.util.TextUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SystemProperties {

    private static final String SYSTEM_LOCALE_PROPERTY_ID = "system.property.locale";

    private static final String SYSTEM_LOCALE_PROPERTY_DESCRIPTION = "system.property.locale.description";
    
    public static final PropertyDefinition<Locale> SYSTEM_LOCALE = PropertyDefinition.builder(Locale.class)
            .id(SYSTEM_LOCALE_PROPERTY_ID)
            .name(() -> TextUtils.getText(SYSTEM_LOCALE_PROPERTY_ID))
            .description(() -> TextUtils.getText(SYSTEM_LOCALE_PROPERTY_DESCRIPTION))
            .category(SystemPropertyCategories.GENERAL)
            .defaultValue(Locale.forLanguageTag("en"))
            .serializer(Locale::getLanguage)
            .deserializer(Locale::forLanguageTag)
            .build();

    public static final List<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(SYSTEM_LOCALE);
}
