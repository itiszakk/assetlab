package com.itiszakk.assetlab.system.service;

import com.itiszakk.assetlab.system.type.PropertyDefinition;

public interface PropertyService {

    void register(PropertyDefinition<?> properties);

    @SuppressWarnings("java:S1452")
    PropertyDefinition<?> getProperty(String id);
}
