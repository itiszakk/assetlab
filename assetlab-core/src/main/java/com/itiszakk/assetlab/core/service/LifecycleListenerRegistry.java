package com.itiszakk.assetlab.core.service;

public interface LifecycleListenerRegistry<T> {

    void register(LifecycleListener<T> listener);
}
