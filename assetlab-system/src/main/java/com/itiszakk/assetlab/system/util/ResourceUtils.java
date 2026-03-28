package com.itiszakk.assetlab.system.util;

import java.net.URL;
import java.util.ResourceBundle;

import com.itiszakk.assetlab.system.configuration.SystemProperties;

public final class ResourceUtils {

    private ResourceUtils() {}

    public static URL load(String name) {
        try {
            return ResourceUtils.class.getClassLoader().getResource(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResourceBundle loadBundle(String name, ClassLoader classLoader) {
        try {
            return ResourceBundle.getBundle(name, SystemProperties.LOCALE.getValue(), classLoader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
