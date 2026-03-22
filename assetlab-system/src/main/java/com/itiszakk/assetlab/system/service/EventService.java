package com.itiszakk.assetlab.system.service;

import java.util.Collection;
import java.util.Map;

import com.itiszakk.assetlab.system.service.listener.CollectionEventListener;
import com.itiszakk.assetlab.system.service.listener.MapEventListener;
import com.itiszakk.assetlab.system.service.listener.SingleEventListener;
import com.itiszakk.assetlab.system.service.listener.VoidEventListener;
import com.itiszakk.assetlab.system.type.event.EventDefinition;

public interface EventService {

    <T> void subscribe(EventDefinition<T> event, SingleEventListener<T> listener);

    <T> void subscribe(EventDefinition<T> event, CollectionEventListener<T> listeners);

    <T> void subscribe(EventDefinition<T> event, MapEventListener<T> listener);

    default void subscribe(EventDefinition<Void> event, VoidEventListener listener) {
        subscribe(event, (SingleEventListener<Void>) nothing -> listener.onEvent());
    }

    <T> void send(EventDefinition<T> event, T single);

    <T> void send(EventDefinition<T> event, Collection<T> collection);

    <T> void send(EventDefinition<T> event, Map<String, T> map);

    default void send(EventDefinition<Void> event) {
        send(event, (Void) null);
    }
}
