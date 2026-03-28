package com.itiszakk.assetlab.desktop.type.scene;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Menu;

public class LocalizedMenu extends Menu implements LocalizationKey {

    private StringProperty key;

    @Override
    public StringProperty targetProperty() {
        return textProperty();
    }

    @Override
    public StringProperty keyProperty() {
        if (key == null) {
            key = new SimpleStringProperty(this, LocalizationKey.KEY_PROPERTY);
        }

        return key;
    }

    @Override
    public String getKey() {
        return LocalizationKey.super.getKey();
    }

    @Override
    public void setKey(String key) {
        LocalizationKey.super.setKey(key);
    }
}
