package com.itiszakk.assetlab.core.service;

import java.util.List;

public interface LifecycleListener<T> {

    default void afterSave(T entity) {
        /* No-op by default */
    }

    default void afterSaveAll(List<T> entities) {
        /* No-op by default */
    }

    default void afterDelete(T entity) {
        /* No-op by default */
    }

    default void afterDeleteAll(List<T> entities) {
        /* No-op by default */
    }
}
