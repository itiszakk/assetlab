package com.itiszakk.assetlab.system.type;

import com.itiszakk.assetlab.system.util.TextUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SystemPropertyCategories {

    private static final String GENERAL_CATEGORY_ID = "system.property.category.general";

    public static final PropertyCategory GENERAL = PropertyCategory.builder()
            .id(GENERAL_CATEGORY_ID)
            .name(() -> TextUtils.getText(GENERAL_CATEGORY_ID))
            .build();
}
