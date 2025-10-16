package com.itiszakk.assetlab.desktop.type.item;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AssetItem {

    @EqualsAndHashCode.Include
    private final String assetId;

    private final String assetDisplayName;


    @Override
    public String toString() {
        return assetDisplayName;
    }
}