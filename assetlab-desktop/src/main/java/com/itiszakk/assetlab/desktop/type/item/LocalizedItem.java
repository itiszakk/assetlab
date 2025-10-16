package com.itiszakk.assetlab.desktop.type.item;

import com.itiszakk.assetlab.system.util.TextUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalizedItem {

    private final String id;

    public static LocalizedItem of(String id) {
        return new LocalizedItem(id);
    }

    @Override
    public String toString() {
        return TextUtils.getText(id);
    }
}
