package com.itiszakk.assetlab.desktop.util;

import java.util.HashMap;
import java.util.Map;

import com.itiszakk.assetlab.desktop.configuration.ApplicationComponent;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final Map<Class<?>, Object> controllers = new HashMap<>();

    public ControllerFactory(ApplicationComponent component) {
        register(component.getMainController());
        register(component.getSettingsController());
        register(component.getAboutController());
        register(component.getAssetsController());
        register(component.getPreviewController());
        register(component.getMetadataController());
    }

    private void register(Object controller) {
        controllers.put(controller.getClass(), controller);
    }

    @Override
    public Object call(Class<?> type) {
        return controllers.get(type);
    }
}
