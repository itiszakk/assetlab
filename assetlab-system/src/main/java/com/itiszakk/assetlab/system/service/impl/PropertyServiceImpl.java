package com.itiszakk.assetlab.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.type.PropertyDefinition;

public class PropertyServiceImpl implements PropertyService {

    private final Map<String, PropertyDefinition<?>> properties = new HashMap<>();

    @Inject
    public PropertyServiceImpl() {

    }

    @Override
    public void register(PropertyDefinition<?> property) {
        properties.put(property.getId(), property);
    }

    @Override
    public PropertyDefinition<?> getProperty(String id) {
        return properties.get(id);
    }
}
