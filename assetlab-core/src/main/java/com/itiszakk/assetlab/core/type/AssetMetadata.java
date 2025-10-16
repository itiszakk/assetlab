package com.itiszakk.assetlab.core.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssetMetadata {

    private final String assetId;

    private String assetDisplayName;

    private LocalDateTime timestamp;

    private String description;

    private List<Tag> tags;

    public List<Tag> getTags() {

        if (tags == null) {
            tags = new ArrayList<>(0);
        }

        return tags;
    }
}
