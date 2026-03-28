package com.itiszakk.assetlab.system.service;

public interface TextService {

    String getText(String code, Object... args);

    String getTextOrDefault(String code, String defaultValue, Object... args);

    void register(String bundle, ClassLoader classLoader);
}
