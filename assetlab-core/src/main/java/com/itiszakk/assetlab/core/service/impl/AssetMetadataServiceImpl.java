package com.itiszakk.assetlab.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.type.AssetMetadata;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class AssetMetadataServiceImpl
        extends AbstractLifecycleService<AssetMetadata>
        implements AssetMetadataService {

    private final Map<String, AssetMetadata> storage = new HashMap<>();

    @Override
    public AssetMetadata load(String id) {
        return storage.get(id);
    }

    @Override
    public void save(AssetMetadata metadata) {
        storage.put(metadata.getAssetId(), metadata);
        notifyAfterSave(metadata);
    }

    @Override
    public void saveAll(List<AssetMetadata> metadata) {
        metadata.forEach(m -> storage.put(m.getAssetId(), m));
        notifyAfterSaveAll(metadata);
    }

    @Override
    public void delete(String id) {
        AssetMetadata removed = storage.remove(id);
        notifyAfterDelete(removed);
    }

    @Override
    public void deleteAll(List<String> ids) {

        List<AssetMetadata> removed = ids.stream()
                .map(storage::remove)
                .toList();

        notifyAfterDeleteAll(removed);
    }
}
