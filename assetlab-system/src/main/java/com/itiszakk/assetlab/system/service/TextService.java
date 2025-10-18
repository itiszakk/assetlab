package com.itiszakk.assetlab.system.service;

public interface TextService {

    String getText(String code, Object... args);

    void register(String bundle, ClassLoader classLoader);
}
