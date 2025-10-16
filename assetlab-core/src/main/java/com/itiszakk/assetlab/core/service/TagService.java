package com.itiszakk.assetlab.core.service;

import java.util.List;

import com.itiszakk.assetlab.core.type.Tag;

public interface TagService {

    List<Tag> load(String id);

    void save(Tag tag);

    void delete(String id, String name);
}
