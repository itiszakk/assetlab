package com.itiszakk.assetlab.desktop.configuration;

import com.itiszakk.assetlab.system.type.event.EventDefinition;

public final class DesktopEvents {

    private DesktopEvents() {}

    public static final EventDefinition<String> ASSET_ITEM_SELECTED = EventDefinition.single(String.class)
            .id("asset.item.selected")
            .build();

    public static final EventDefinition<Void> ASSET_ITEM_DESELECTED = EventDefinition.single()
            .id("asset.item.deselected")
            .build();
}
