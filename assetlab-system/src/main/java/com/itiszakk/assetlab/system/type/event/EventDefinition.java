package com.itiszakk.assetlab.system.type.event;

import java.util.Objects;

public class EventDefinition<T> {

    private final Class<T> type;
    private final EventKind kind;
    private final String id;

    private EventDefinition(Builder<T> builder) {
        type = builder.type;
        kind = builder.kind;
        id = builder.id;
    }

    public Class<T> getType() {
        return type;
    }

    public EventKind getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public static Builder<Void> single() {
        return new Builder<>(Void.class, EventKind.SINGLE);
    }

    public static <T> Builder<T> single(Class<T> type) {
        return new Builder<>(type, EventKind.SINGLE);
    }

    public static Builder<Void> collection() {
        return new Builder<>(Void.class, EventKind.COLLECTION);
    }

    public static <T> Builder<T> collection(Class<T> type) {
        return new Builder<>(type, EventKind.COLLECTION);
    }

    public static Builder<Void> map() {
        return new Builder<>(Void.class, EventKind.MAP);
    }

    public static <T> Builder<T> map(Class<T> type) {
        return new Builder<>(type, EventKind.MAP);
    }

    public static final class Builder<T> {

        private final Class<T> type;
        private final EventKind kind;
        private String id;

        private Builder(Class<T> type, EventKind kind) {
            this.type = type;
            this.kind = kind;
        }

        public Builder<T> id(String id) {
            this.id = id;
            return this;
        }

        public EventDefinition<T> build() {

            Objects.requireNonNull(type, "Event type cannot be null");
            Objects.requireNonNull(kind, "Event kind cannot be null");

            return new EventDefinition<>(this);
        }
    }
}
