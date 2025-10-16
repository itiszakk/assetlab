package com.itiszakk.assetlab.system.type;

import java.util.function.Function;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDefinition<T> {

    private final Class<T> type;

    private final String id;

    private final T defaultValue;

    private final Predicate<T> validator;

    private final Function<T, String> serializer;

    private final Function<String, T> deserializer;

    private T value;

    private PropertyDefinition(PropertyDefinitionBuilder<T> builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.defaultValue = builder.defaultValue;
        this.value = builder.defaultValue;
        this.validator = builder.validator;
        this.serializer = builder.serializer;
        this.deserializer = builder.deserializer;
    }

    public static <T> PropertyDefinitionBuilder<T> builder(Class<T> type) {
        return new PropertyDefinitionBuilder<>(type);
    }

    public static class PropertyDefinitionBuilder<T> {

        private final Class<T> type;

        private String id;

        private T defaultValue;

        private Predicate<T> validator;

        private Function<T, String> serializer;

        private Function<String, T> deserializer;

        private PropertyDefinitionBuilder(Class<T> type) {
            this.type = type;
        }

        public PropertyDefinitionBuilder<T> id(String id) {
            this.id = id;
            return this;
        }

        public PropertyDefinitionBuilder<T> defaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public PropertyDefinitionBuilder<T> validator(Predicate<T> validator) {
            this.validator = validator;
            return this;
        }

        public PropertyDefinitionBuilder<T> serializer(Function<T, String> serializer) {
            this.serializer = serializer;
            return this;
        }

        public PropertyDefinitionBuilder<T> deserializer(Function<String, T> deserializer) {
            this.deserializer = deserializer;
            return this;
        }

        public PropertyDefinition<T> build() {
            return new PropertyDefinition<>(this);
        }
    }
}