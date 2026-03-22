package com.itiszakk.assetlab.system.type.property;

import java.util.Objects;
import java.util.function.Supplier;

public class PropertyCategory {

    private final String id;
    private final Supplier<String> name;

    private PropertyCategory(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        PropertyCategory that = (PropertyCategory) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private Supplier<String> name;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(Supplier<String> name) {
            this.name = name;
            return this;
        }

        public PropertyCategory build() {
            Objects.requireNonNull(id, "Property category id cannot be null");
            return new PropertyCategory(this);
        }
    }
}
