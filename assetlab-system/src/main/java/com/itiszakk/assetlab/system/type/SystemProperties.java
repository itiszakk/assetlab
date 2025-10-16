package com.itiszakk.assetlab.system.type;

import java.util.List;
import java.util.Locale;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SystemProperties {

    public static final PropertyDefinition<Locale> PROPERTY_LOCALE_DEFINITION = PropertyDefinition.builder(Locale.class)
            .id("system.locale")
            .defaultValue(Locale.forLanguageTag("en"))
            .serializer(Locale::getLanguage)
            .deserializer(Locale::forLanguageTag)
            .build();

    public static final List<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(PROPERTY_LOCALE_DEFINITION);
}
