package com.itiszakk.assetlab.system.service.listener;

import java.util.Map;

@FunctionalInterface
public interface MapEventListener<T> extends EventListener {

    void onEvent(Map<String, T> payload);
}
