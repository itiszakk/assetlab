package com.itiszakk.assetlab.system.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.type.property.PropertyCategory;
import com.itiszakk.assetlab.system.type.property.PropertyDefinition;

public class PropertyServiceImpl implements PropertyService {

    private final Map<String, PropertyDefinition<?>> properties = new HashMap<>();

    @Override
    public void register(Collection<PropertyDefinition<?>> definitions) {
        definitions.forEach(definition -> properties.put(definition.getId(), definition));
    }

    @Override
    public PropertyDefinition<?> getProperty(String id) {
        return properties.get(id);
    }

    @Override
    public Collection<PropertyDefinition<?>> getProperties() {
        return properties.values();
    }

    @Override
    public Map<PropertyCategory, List<PropertyDefinition<?>>> getPropertiesByCategory() {
        return properties.values().stream()
                .collect(Collectors.groupingBy(PropertyDefinition::getCategory));
    }
}
