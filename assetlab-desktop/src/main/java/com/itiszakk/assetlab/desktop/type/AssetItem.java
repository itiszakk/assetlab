package com.itiszakk.assetlab.desktop.type;

import java.util.Objects;

public class AssetItem {

    private final String assetId;
    private final String assetDisplayName;

    private AssetItem(Builder builder) {
        assetId = builder.assetId;
        assetDisplayName = builder.assetDisplayName;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getAssetDisplayName() {
        return assetDisplayName;
    }

    @Override
    public String toString() {
        return assetDisplayName;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        AssetItem assetItem = (AssetItem) object;
        return Objects.equals(assetId, assetItem.assetId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(assetId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String assetId;
        private String assetDisplayName;

        private Builder() {}

        public Builder assetId(String assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder assetDisplayName(String assetDisplayName) {
            this.assetDisplayName = assetDisplayName;
            return this;
        }

        public AssetItem build() {
            return new AssetItem(this);
        }
    }
}
