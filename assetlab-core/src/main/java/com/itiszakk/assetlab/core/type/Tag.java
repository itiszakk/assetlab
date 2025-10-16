package com.itiszakk.assetlab.core.type;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tag {

    private final String assetId;

    private final String name;
}
