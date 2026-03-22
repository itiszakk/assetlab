package com.itiszakk.assetlab.system.service.listener;

@FunctionalInterface
public interface VoidEventListener extends EventListener {

    void onEvent();
}
