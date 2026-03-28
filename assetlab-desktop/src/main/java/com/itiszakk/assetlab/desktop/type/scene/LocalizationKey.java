package com.itiszakk.assetlab.desktop.type.scene;

import org.apache.commons.lang3.StringUtils;

import com.itiszakk.assetlab.desktop.configuration.DesktopProperties;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;

public interface LocalizationKey {

    String KEY_PROPERTY = "key";

    StringProperty targetProperty();

    StringProperty keyProperty();

    default String getKey() {
        return keyProperty().get();
    }

    default void setKey(String key) {

        keyProperty().set(key);

        if (StringUtils.isNotBlank(key)) {
            targetProperty().bind(Bindings.createStringBinding(
                    () -> TextUtils.getText(key),
                    DesktopProperties.LOCALE_WRAPPER.getProperty()));
        }
    }
}
