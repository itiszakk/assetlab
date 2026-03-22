package com.itiszakk.assetlab.core.configuration;

import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.core.type.Tag;
import com.itiszakk.assetlab.system.type.event.EventDefinition;

public final class CoreEvents {

    private static final String EVENT_ID_PREFIX = CoreModule.MODULE_ID + ".event";

    private CoreEvents() {}

    public static final EventDefinition<Asset> ASSETS_SAVED = EventDefinition.collection(Asset.class)
            .id(EVENT_ID_PREFIX + ".assets.saved")
            .build();

    public static final EventDefinition<Asset> ASSETS_DELETED = EventDefinition.collection(Asset.class)
            .id(EVENT_ID_PREFIX + ".assets.deleted")
            .build();

    public static final EventDefinition<AssetMetadata> ASSETS_METADATA_SAVED = EventDefinition.collection(AssetMetadata.class)
            .id(EVENT_ID_PREFIX + ".assets.metadata.saved")
            .build();

    public static final EventDefinition<AssetMetadata> ASSETS_METADATA_DELETED = EventDefinition.collection(AssetMetadata.class)
            .id(EVENT_ID_PREFIX + ".assets.metadata.deleted")
            .build();

    public static final EventDefinition<Tag> TAGS_SAVED = EventDefinition.collection(Tag.class)
            .id(EVENT_ID_PREFIX + ".tags.saved")
            .build();

    public static final EventDefinition<Tag> TAGS_DELETED = EventDefinition.collection(Tag.class)
            .id(CoreModule.MODULE_ID + ".tags.deleted")
            .build();
}
