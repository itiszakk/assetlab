package com.itiszakk.assetlab.system.configuration;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class<?>, Object> instances = new HashMap<>();

    public <T> void register(Class<T> type, T instance) {
        instances.put(type, instance);
    }

    public <T> T get(Class<T> type) {
        return type.cast(instances.get(type));
    }
}
