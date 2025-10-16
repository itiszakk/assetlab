package com.itiszakk.assetlab.system.service;

import java.util.Collection;
import java.util.Collections;

import com.itiszakk.assetlab.system.type.PropertyDefinition;

public interface ModuleDefinition {

    String getId();

    int getInitOrder();

    default void start() {}

    @SuppressWarnings("java:S1452")
    default Collection<PropertyDefinition<?>> getProperties() {
        return Collections.emptyList();
    }

    default String getTextBundle() {
        return null;
    }
}