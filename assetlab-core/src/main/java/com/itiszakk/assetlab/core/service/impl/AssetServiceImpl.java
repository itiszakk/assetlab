package com.itiszakk.assetlab.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.Asset;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class AssetServiceImpl
        extends AbstractLifecycleService<Asset>
        implements AssetService {

    private final Map<String, Asset> storage = new HashMap<>();

    @Override
    public Asset load(String id) {
        return storage.get(id);
    }

    @Override
    public List<Asset> loadAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void save(Asset asset) {
        storage.put(asset.getId(), asset);
        notifyAfterSave(asset);
    }

    @Override
    public void saveAll(List<Asset> assets) {
        assets.forEach(asset -> storage.put(asset.getId(), asset));
        notifyAfterSaveAll(assets);
    }

    @Override
    public void delete(String id) {
        Asset removed = storage.remove(id);
        notifyAfterDelete(removed);
    }

    @Override
    public void deleteAll(List<String> ids) {

        List<Asset> removed = ids.stream()
                .map(storage::remove)
                .toList();

        notifyAfterDeleteAll(removed);
    }
}
