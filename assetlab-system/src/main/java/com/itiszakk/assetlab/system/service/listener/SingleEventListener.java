package com.itiszakk.assetlab.system.service.listener;

@FunctionalInterface
public interface SingleEventListener<T> extends EventListener {

    void onEvent(T payload);
}
