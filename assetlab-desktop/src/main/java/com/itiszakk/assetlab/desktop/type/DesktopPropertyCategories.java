package com.itiszakk.assetlab.desktop.type;

import com.itiszakk.assetlab.system.type.PropertyCategory;
import com.itiszakk.assetlab.system.util.TextUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DesktopPropertyCategories {

    private static final String APPEARANCE_CATEGORY_ID = "desktop.property.category.appearance";

    public static final PropertyCategory APPEARANCE = PropertyCategory.builder()
            .id(APPEARANCE_CATEGORY_ID)
            .name(() -> TextUtils.getText(APPEARANCE_CATEGORY_ID))
            .build();
}
