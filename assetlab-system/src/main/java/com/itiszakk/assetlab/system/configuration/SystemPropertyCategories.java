package com.itiszakk.assetlab.system.configuration;

import com.itiszakk.assetlab.system.type.property.PropertyCategory;
import com.itiszakk.assetlab.system.util.TextUtils;

public final class SystemPropertyCategories {

    private static final String GENERAL_CATEGORY_ID = SystemModule.MODULE_ID + ".property.category.general";

    private SystemPropertyCategories() {}

    public static final PropertyCategory GENERAL = PropertyCategory.builder()
            .id(GENERAL_CATEGORY_ID)
            .name(() -> TextUtils.getText(GENERAL_CATEGORY_ID))
            .build();
}
