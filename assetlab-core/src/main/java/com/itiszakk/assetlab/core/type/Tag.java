package com.itiszakk.assetlab.core.type;

public class Tag {

    private final String assetId;
    private final String name;

    private Tag(Builder builder) {
        assetId = builder.assetId;
        name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAssetId() {
        return assetId;
    }

    public String getName() {
        return name;
    }

    public static final class Builder {

        private String assetId;
        private String name;

        private Builder() {}

        public Builder assetId(String assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }
}
