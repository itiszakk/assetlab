package com.itiszakk.assetlab.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import com.itiszakk.assetlab.system.service.EventService;
import com.itiszakk.assetlab.system.service.listener.CollectionEventListener;
import com.itiszakk.assetlab.system.service.listener.EventListener;
import com.itiszakk.assetlab.system.service.listener.MapEventListener;
import com.itiszakk.assetlab.system.service.listener.SingleEventListener;
import com.itiszakk.assetlab.system.type.event.EventDefinition;
import com.itiszakk.assetlab.system.type.event.EventKind;

public class EventServiceImpl implements EventService {

    private final Map<EventDefinition<?>, List<EventListener>> listeners = new HashMap<>();

    @Override
    public <T> void subscribe(EventDefinition<T> event, SingleEventListener<T> listener) {
        subscribe(event, (EventListener) listener);
    }

    @Override
    public <T> void subscribe(EventDefinition<T> event, CollectionEventListener<T> listener) {
        subscribe(event, (EventListener) listener);
    }

    @Override
    public <T> void subscribe(EventDefinition<T> event, MapEventListener<T> listener) {
        subscribe(event, (EventListener) listener);
    }

    private void subscribe(EventDefinition<?> event, EventListener listener) {
        if (ObjectUtils.allNotNull(event, listener)) {
            listeners.computeIfAbsent(event, k -> new ArrayList<>())
                    .add(listener);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void send(EventDefinition<T> event, T payload) {

        if (event.getKind() != EventKind.SINGLE) {
            throw new IllegalArgumentException("Event + [" + event + "] payload is not single");
        }

        listeners.getOrDefault(event, Collections.emptyList())
                .forEach(listener -> ((SingleEventListener<T>) listener).onEvent(payload));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void send(EventDefinition<T> event, Collection<T> payload) {

        if (event.getKind() != EventKind.COLLECTION) {
            throw new IllegalArgumentException("Event + [" + event + "] payload is not collection");
        }

        listeners.getOrDefault(event, Collections.emptyList())
                .forEach(listener -> ((CollectionEventListener<T>) listener).onEvent(payload));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void send(EventDefinition<T> event, Map<String, T> payload) {

        if (event.getKind() != EventKind.COLLECTION) {
            throw new IllegalArgumentException("Event + [" + event + "] payload is not map");
        }

        listeners.getOrDefault(event, Collections.emptyList())
                .forEach(listener -> ((MapEventListener<T>) listener).onEvent(payload));
    }
}
