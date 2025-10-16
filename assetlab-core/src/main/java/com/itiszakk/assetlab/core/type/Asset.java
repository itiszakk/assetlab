package com.itiszakk.assetlab.core.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Asset {

    private final String id;

    private final String name;

    private final String extension;

    private final String path;

    private final long size;

    private final int width;

    private final int height;
}