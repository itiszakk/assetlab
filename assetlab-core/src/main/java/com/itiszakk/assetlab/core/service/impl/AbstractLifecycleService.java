package com.itiszakk.assetlab.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itiszakk.assetlab.core.service.LifecycleListener;
import com.itiszakk.assetlab.core.service.LifecycleListenerRegistry;

public abstract class AbstractLifecycleService<T> implements LifecycleListenerRegistry<T> {

    private final List<LifecycleListener<T>> listeners = new ArrayList<>();

    protected void notifyAfterSave(T entity) {
        listeners.forEach(l -> l.afterSave(entity));
    }

    protected void notifyAfterSaveAll(List<T> entities) {
        listeners.forEach(l -> l.afterSaveAll(entities));
    }

    protected void notifyAfterDelete(T entity) {
        listeners.forEach(l -> l.afterDelete(entity));
    }

    protected void notifyAfterDeleteAll(List<T> entities) {
        listeners.forEach(l -> l.afterDeleteAll(entities));
    }

    @Override
    public void register(LifecycleListener<T> listener) {
        listeners.add(listener);
    }
}
