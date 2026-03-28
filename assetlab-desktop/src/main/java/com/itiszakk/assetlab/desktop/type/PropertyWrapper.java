package com.itiszakk.assetlab.desktop.type;

import com.itiszakk.assetlab.system.type.property.PropertyDefinition;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PropertyWrapper<T> {

    private final PropertyDefinition<T> definition;
    private final ObjectProperty<T> property;

    private PropertyWrapper(PropertyDefinition<T> definition, ObjectProperty<T> property) {
        this.definition = definition;
        this.property = property;
    }

    public static <T> PropertyWrapper<T> of(PropertyDefinition<T> definition) {
        SimpleObjectProperty<T> property = new SimpleObjectProperty<>(definition.getValue());
        return new PropertyWrapper<>(definition, property);
    }

    public void set(T value) {
        definition.setValue(value);
        property.set(value);
    }

    public T get() {
        return property.get();
    }

    public ObjectProperty<T> getProperty() {
        return property;
    }
}
