package com.itiszakk.assetlab.desktop.type;

public enum DesktopTheme {

    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system");

    private final String name;

    DesktopTheme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
