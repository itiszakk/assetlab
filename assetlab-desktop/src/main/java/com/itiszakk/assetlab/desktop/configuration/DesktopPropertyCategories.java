package com.itiszakk.assetlab.desktop.configuration;

import com.itiszakk.assetlab.system.type.property.PropertyCategory;
import com.itiszakk.assetlab.system.util.TextUtils;

public final class DesktopPropertyCategories {

    private static final String APPEARANCE_CATEGORY_ID = DesktopModule.MODULE_ID + ".property.category.appearance";

    private DesktopPropertyCategories() {}

    public static final PropertyCategory APPEARANCE = PropertyCategory.builder()
            .id(APPEARANCE_CATEGORY_ID)
            .name(() -> TextUtils.getText(APPEARANCE_CATEGORY_ID))
            .build();
}
