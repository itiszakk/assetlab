package com.itiszakk.assetlab.core.service;

import java.util.List;

import com.itiszakk.assetlab.core.type.Asset;

public interface AssetService extends LifecycleListenerRegistry<Asset> {

    Asset load(String id);

    List<Asset> loadAll();

    void save(Asset asset);

    void saveAll(List<Asset> assets);

    void delete(String id);

    void deleteAll(List<String> ids);
}