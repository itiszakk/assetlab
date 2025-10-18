package com.itiszakk.assetlab.system.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.type.PropertyCategory;
import com.itiszakk.assetlab.system.type.PropertyDefinition;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class PropertyServiceImpl implements PropertyService {

    private final Map<String, PropertyDefinition<?>> properties = new HashMap<>();

    @Override
    public void register(PropertyDefinition<?> property) {
        properties.put(property.getId(), property);
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
