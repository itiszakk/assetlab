package com.itiszakk.assetlab.desktop.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import com.itiszakk.assetlab.desktop.type.DesktopTheme;
import com.itiszakk.assetlab.desktop.type.PropertyWrapper;
import com.itiszakk.assetlab.system.configuration.SystemProperties;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

public final class DesktopProperties {

    private static final String THEME_PROPERTY_ID = DesktopModule.MODULE_ID + ".property.theme";
    private static final String THEME_PROPERTY_DESCRIPTION = DesktopModule.MODULE_ID + ".property.theme.description";

    private DesktopProperties() {}

    public static final PropertyDefinition<DesktopTheme> THEME = PropertyDefinition.builder(DesktopTheme.class)
            .id(THEME_PROPERTY_ID)
            .name(() -> TextUtils.getText(THEME_PROPERTY_ID))
            .description(() -> TextUtils.getText(THEME_PROPERTY_DESCRIPTION))
            .category(DesktopPropertyCategories.APPEARANCE)
            .defaultValue(DesktopTheme.SYSTEM)
            .serializer(DesktopTheme::getName)
            .deserializer(DesktopTheme::valueOf)
            .build();

    public static final PropertyWrapper<Locale> LOCALE_WRAPPER = PropertyWrapper.of(SystemProperties.LOCALE);
    public static final PropertyWrapper<DesktopTheme> THEME_WRAPPER = PropertyWrapper.of(THEME);

    public static final Collection<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(THEME);
}
