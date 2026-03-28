package com.itiszakk.assetlab.system.type.property;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

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

    private PropertyDefinition(Builder<T> builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.category = builder.category;
        this.defaultValue = builder.defaultValue;
        this.serializer = builder.serializer;
        this.deserializer = builder.deserializer;
        this.value = builder.defaultValue;
    }

    public Class<T> getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public PropertyCategory getCategory() {
        return category;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String serialize() {
        return serializer.apply(value);
    }

    public T deserialize(String input) {
        return deserializer.apply(input);
    }

    public static Builder<String> string() {
        return new Builder<>(String.class);
    }

    public static <T> Builder<T> builder(Class<T> type) {
        return new Builder<>(type);
    }

    public static class Builder<T> {

        private final Class<T> type;
        private String id;
        private Supplier<String> name;
        private Supplier<String> description;
        private PropertyCategory category;
        private T defaultValue;
        private Function<T, String> serializer;
        private Function<String, T> deserializer;

        private Builder(Class<T> type) {
            this.type = type;
        }

        public Builder<T> id(String id) {
            this.id = id;
            return this;
        }

        public Builder<T> name(Supplier<String> name) {
            this.name = name;
            return this;
        }

        public Builder<T> description(Supplier<String> description) {
            this.description = description;
            return this;
        }

        public Builder<T> category(PropertyCategory category) {
            this.category = category;
            return this;
        }

        public Builder<T> defaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder<T> serializer(Function<T, String> serializer) {
            this.serializer = serializer;
            return this;
        }

        public Builder<T> deserializer(Function<String, T> deserializer) {
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
