package com.itiszakk.assetlab.desktop.type;

import lombok.Getter;

@Getter
public enum DesktopTheme {

    LIGHT("light"),

    DARK("dark"),

    SYSTEM("system");

    private final String name;

    DesktopTheme(String name) {
        this.name = name;
    }
}
