package com.itiszakk.assetlab.core.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itiszakk.assetlab.core.configuration.CoreEvents;
import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.system.configuration.ApplicationContext;
import com.itiszakk.assetlab.system.service.EventService;

public class AssetMetadataServiceImpl implements AssetMetadataService {

    private final Map<String, AssetMetadata> storage = new HashMap<>();
    private final EventService eventService;

    public AssetMetadataServiceImpl(ApplicationContext context) {
        this.eventService = context.get(EventService.class);
    }

    @Override
    public AssetMetadata load(String id) {
        return storage.get(id);
    }

    @Override
    public void save(AssetMetadata metadata) {
        storage.put(metadata.getAssetId(), metadata);
        eventService.send(CoreEvents.ASSETS_METADATA_SAVED, Collections.singletonList(metadata));
    }

    @Override
    public void saveAll(List<AssetMetadata> metadata) {
        metadata.forEach(m -> storage.put(m.getAssetId(), m));
        eventService.send(CoreEvents.ASSETS_METADATA_SAVED, metadata);
    }

    @Override
    public void delete(String id) {
        AssetMetadata removed = storage.remove(id);
        eventService.send(CoreEvents.ASSETS_METADATA_DELETED, Collections.singletonList(removed));
    }

    @Override
    public void deleteAll(List<String> ids) {

        List<AssetMetadata> removed = ids.stream()
                .map(storage::remove)
                .toList();

        eventService.send(CoreEvents.ASSETS_METADATA_DELETED, removed);
    }
}
