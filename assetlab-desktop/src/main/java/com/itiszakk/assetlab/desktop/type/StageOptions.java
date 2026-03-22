package com.itiszakk.assetlab.desktop.type;

public enum StageOptions {

    TITLE("title"),
    MIN_WIDTH("min.width"),
    MIN_HEIGHT("min.height"),
    MAXIMIZED("maximized");

    private final String name;

    StageOptions(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
