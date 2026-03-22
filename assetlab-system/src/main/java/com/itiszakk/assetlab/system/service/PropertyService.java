package com.itiszakk.assetlab.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.itiszakk.assetlab.system.type.property.PropertyCategory;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;

public interface PropertyService {

    void register(Collection<PropertyDefinition<?>> definitions);

    PropertyDefinition<?> getProperty(String id);

    Collection<PropertyDefinition<?>> getProperties();

    Map<PropertyCategory, List<PropertyDefinition<?>>> getPropertiesByCategory();
}
