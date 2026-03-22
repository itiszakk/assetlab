package com.itiszakk.assetlab.core.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AssetMetadata {

    private final String assetId;
    private String assetDisplayName;
    private LocalDateTime timestamp;
    private String description;
    private List<Tag> tags;

    private AssetMetadata(Builder builder) {
        assetId = builder.assetId;
        assetDisplayName = builder.assetDisplayName;
        timestamp = builder.timestamp;
        description = builder.description;
        tags = builder.tags;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getAssetDisplayName() {
        return assetDisplayName;
    }

    public void setAssetDisplayName(String assetDisplayName) {
        this.assetDisplayName = assetDisplayName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Tag> getTags() {

        if (tags == null) {
            tags = new ArrayList<>(0);
        }

        return tags;
    }

    public static final class Builder {

        private String assetId;
        private String assetDisplayName;
        private LocalDateTime timestamp;
        private String description;
        private List<Tag> tags;

        private Builder() {}

        public Builder assetId(String assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder assetDisplayName(String assetDisplayName) {
            this.assetDisplayName = assetDisplayName;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public AssetMetadata build() {
            return new AssetMetadata(this);
        }
    }
}
