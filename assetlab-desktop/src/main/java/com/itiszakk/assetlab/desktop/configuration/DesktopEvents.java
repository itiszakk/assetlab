package com.itiszakk.assetlab.desktop.configuration;

import com.itiszakk.assetlab.desktop.type.AssetItem;
import com.itiszakk.assetlab.system.type.event.EventDefinition;

public final class DesktopEvents {

    private DesktopEvents() {}

    public static final EventDefinition<AssetItem> ASSET_ITEM_SELECTED = EventDefinition.single(AssetItem.class)
            .id("asset.item.selected")
            .build();

    public static final EventDefinition<Void> ASSET_ITEM_DESELECTED = EventDefinition.single()
            .id("asset.item.deselected")
            .build();
}
