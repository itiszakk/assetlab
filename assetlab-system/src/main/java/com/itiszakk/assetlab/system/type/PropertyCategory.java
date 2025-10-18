package com.itiszakk.assetlab.system.type;

import java.util.Objects;
import java.util.function.Supplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PropertyCategory {

    @EqualsAndHashCode.Include
    private final String id;

    private final Supplier<String> name;

    private PropertyCategory(PropertyCategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public String toString() {
        return name.get();
    }

    public static PropertyCategoryBuilder builder() {
        return new PropertyCategoryBuilder();
    }

    public static class PropertyCategoryBuilder {

        private String id;

        private Supplier<String> name;

        public PropertyCategoryBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PropertyCategoryBuilder name(Supplier<String> name) {
            this.name = name;
            return this;
        }

        public PropertyCategory build() {
            Objects.requireNonNull(id, "Property category id cannot be null");
            return new PropertyCategory(this);
        }
    }
}
