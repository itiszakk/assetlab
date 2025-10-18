package com.itiszakk.assetlab.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.core.service.TagService;
import com.itiszakk.assetlab.core.type.Tag;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class TagServiceImpl implements TagService {

    private final Map<String, Map<String, Tag>> storage = new HashMap<>();

    @Override
    public List<Tag> load(String id) {
        Map<String, Tag> tags = storage.get(id);
        return tags == null
                ? Collections.emptyList()
                : new ArrayList<>(tags.values());
    }

    @Override
    public void save(Tag tag) {
        storage.computeIfAbsent(tag.getAssetId(), k -> new HashMap<>())
                .put(tag.getName(), tag);
    }

    @Override
    public void delete(String id, String name) {
        Map<String, Tag> tags = storage.get(id);
        if (tags != null) {
            tags.remove(name);
        }
    }
}
