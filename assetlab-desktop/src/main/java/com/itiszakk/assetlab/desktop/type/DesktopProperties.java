package com.itiszakk.assetlab.desktop.type;

import java.util.Collection;
import java.util.List;

import com.itiszakk.assetlab.system.type.PropertyDefinition;
import com.itiszakk.assetlab.system.util.TextUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DesktopProperties {

    private static final String THEME_PROPERTY_ID = "desktop.property.theme";

    private static final String THEME_PROPERTY_DESCRIPTION = "desktop.property.theme.description";

    public static final PropertyDefinition<DesktopTheme> THEME = PropertyDefinition.builder(DesktopTheme.class)
            .id(THEME_PROPERTY_ID)
            .name(() -> TextUtils.getText(THEME_PROPERTY_ID))
            .description(() -> TextUtils.getText(THEME_PROPERTY_DESCRIPTION))
            .category(DesktopPropertyCategories.APPEARANCE)
            .defaultValue(DesktopTheme.SYSTEM)
            .serializer(DesktopTheme::getName)
            .deserializer(DesktopTheme::valueOf)
            .build();

    public static final Collection<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(
            THEME
    );
}