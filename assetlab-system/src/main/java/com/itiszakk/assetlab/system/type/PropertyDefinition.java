package com.itiszakk.assetlab.system.type;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDefinition<T> {

    private final Class<T> type;

    private final String id;

    private final Supplier<String> name;

    private final Supplier<String> description;

    private final PropertyCategory category;

    private final T defaultValue;

    private final Function<T, String> serializer;

    private final Function<String, T> deserializer;

    private T value;

    private PropertyDefinition(PropertyDefinitionBuilder<T> builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.category = builder.category;
        this.defaultValue = builder.defaultValue;
        this.value = builder.defaultValue;
        this.serializer = builder.serializer;
        this.deserializer = builder.deserializer;
    }

    public String serialize() {
        return serializer.apply(value);
    }

    public T deserialize(String input) {
        return deserializer.apply(input);
    }

    public static PropertyDefinitionBuilder<String> string() {
        return new PropertyDefinitionBuilder<>(String.class);
    }

    public static <T> PropertyDefinitionBuilder<T> builder(Class<T> type) {
        return new PropertyDefinitionBuilder<>(type);
    }

    public static class PropertyDefinitionBuilder<T> {

        private final Class<T> type;

        private String id;

        private Supplier<String> name;

        private Supplier<String> description;

        private PropertyCategory category;

        private T defaultValue;

        private Function<T, String> serializer;

        private Function<String, T> deserializer;

        private PropertyDefinitionBuilder(Class<T> type) {
            this.type = type;
        }

        public PropertyDefinitionBuilder<T> id(String id) {
            this.id = id;
            return this;
        }

        public PropertyDefinitionBuilder<T> name(Supplier<String> name) {
            this.name = name;
            return this;
        }

        public PropertyDefinitionBuilder<T> description(Supplier<String> description) {
            this.description = description;
            return this;
        }

        public PropertyDefinitionBuilder<T> category(PropertyCategory category) {
            this.category = category;
            return this;
        }

        public PropertyDefinitionBuilder<T> defaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
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

            Objects.requireNonNull(id, "Property id cannot be null");
            Objects.requireNonNull(category, "Property category cannot be null");

            return new PropertyDefinition<>(this);
        }
    }
}