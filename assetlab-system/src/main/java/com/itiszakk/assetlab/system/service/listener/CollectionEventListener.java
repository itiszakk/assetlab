package com.itiszakk.assetlab.system.service.listener;

import java.util.Collection;

@FunctionalInterface
public interface CollectionEventListener<T> extends EventListener{

    void onEvent(Collection<T> payload);
}
