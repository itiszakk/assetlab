package com.itiszakk.assetlab.desktop.type;

import java.util.Collection;
import java.util.List;

import com.itiszakk.assetlab.system.type.PropertyDefinition;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DesktopProperties {

    public static final String PROPERTY_THEME = "desktop.general.theme";

    public static final PropertyDefinition<DesktopTheme> PROPERTY_THEME_DEFINITION = PropertyDefinition.builder(DesktopTheme.class)
            .id(PROPERTY_THEME)
            .defaultValue(DesktopTheme.SYSTEM)
            .build();

    public static final Collection<PropertyDefinition<?>> PROPERTY_DEFINITIONS = List.of(
            PROPERTY_THEME_DEFINITION
    );
}