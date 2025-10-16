package com.itiszakk.assetlab.core.service;

import java.util.List;

import com.itiszakk.assetlab.core.type.AssetMetadata;

public interface AssetMetadataService extends LifecycleListenerRegistry<AssetMetadata> {

    AssetMetadata load(String id);

    void save(AssetMetadata metadata);

    void saveAll(List<AssetMetadata> metadata);

    void delete(String id);

    void deleteAll(List<String> ids);
}
