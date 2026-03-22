package com.itiszakk.assetlab.core.type;

public class Asset {

    private final String id;
    private final String name;
    private final String extension;
    private final String path;
    private final long size;
    private final int width;
    private final int height;

    private Asset(Builder builder) {
        id = builder.id;
        name = builder.name;
        extension = builder.extension;
        path = builder.path;
        size = builder.size;
        width = builder.width;
        height = builder.height;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String id;
        private String name;
        private String extension;
        private String path;
        private long size;
        private int width;
        private int height;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder size(long size) {
            this.size = size;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Asset build() {
            return new Asset(this);
        }
    }
}
