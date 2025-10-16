package com.itiszakk.assetlab.desktop.type;

import lombok.Getter;

@Getter
public enum StageProperty {
    TITLE("title"),
    MIN_WIDTH("min.width"),
    MIN_HEIGHT("min.height"),
    MAXIMIZED("maximized");

    private final String name;

    StageProperty(String name) {
        this.name = name;
    }
}
