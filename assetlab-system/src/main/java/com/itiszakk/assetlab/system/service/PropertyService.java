package com.itiszakk.assetlab.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.itiszakk.assetlab.system.type.PropertyCategory;
import com.itiszakk.assetlab.system.type.PropertyDefinition;

@SuppressWarnings("java:S1452")
public interface PropertyService {

    void register(PropertyDefinition<?> properties);

    PropertyDefinition<?> getProperty(String id);

    Collection<PropertyDefinition<?>> getProperties();

    Map<PropertyCategory, List<PropertyDefinition<?>>> getPropertiesByCategory();
}
