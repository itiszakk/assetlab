package com.itiszakk.assetlab.system.configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.itiszakk.assetlab.system.type.PropertyDefinition;

public interface Module {

    String getId();

    default List<String> getDependencies() {
        return Collections.emptyList();
    }

    default Collection<PropertyDefinition<?>> getProperties() {
        return Collections.emptyList();
    }

    default String getTextBundle() {
        return null;
    }

    default void init(ApplicationContext context) {}

    default void start() {}

    default void stop() {}
}
