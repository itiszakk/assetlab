package com.itiszakk.assetlab.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itiszakk.assetlab.core.configuration.CoreEvents;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.service.EventService;

public class AssetServiceImpl implements AssetService {

    private final Map<String, Asset> storage = new HashMap<>();
    private final EventService eventService;

    public AssetServiceImpl(ApplicationContext context) {
        eventService = context.get(EventService.class);
    }

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
        eventService.send(CoreEvents.ASSETS_SAVED, Collections.singletonList(asset));
    }

    @Override
    public void saveAll(List<Asset> assets) {
        assets.forEach(asset -> storage.put(asset.getId(), asset));
        eventService.send(CoreEvents.ASSETS_SAVED, assets);
    }

    @Override
    public void delete(String id) {
        Asset removed = storage.remove(id);
        eventService.send(CoreEvents.ASSETS_DELETED, Collections.singletonList(removed));
    }

    @Override
    public void deleteAll(List<String> ids) {

        List<Asset> removed = ids.stream()
                .map(storage::remove)
                .toList();

        eventService.send(CoreEvents.ASSETS_DELETED, removed);
    }
}
