package com.itiszakk.assetlab.system.util;

import java.net.URL;
import java.util.ResourceBundle;

import com.itiszakk.assetlab.system.type.SystemProperties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtils {

    public static URL load(String name) {
        try {
            return ResourceUtils.class.getClassLoader().getResource(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResourceBundle loadBundle(String name, ClassLoader classLoader) {
        try {
            return ResourceBundle.getBundle(name, SystemProperties.SYSTEM_LOCALE.getValue(), classLoader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
