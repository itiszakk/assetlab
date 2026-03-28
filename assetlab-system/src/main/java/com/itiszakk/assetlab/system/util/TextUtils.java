package com.itiszakk.assetlab.system.util;

import com.itiszakk.assetlab.system.service.TextService;

public final class TextUtils {

    private static TextService textService;

    private TextUtils() {}

    public static void init(TextService textService) {
        TextUtils.textService = textService;
    }

    public static String getText(String code, Object... args) {
        return textService.getText(code, args);
    }

    public static String getTextOrDefault(String code, String defaultValue, Object... args) {
        return textService.getTextOrDefault(code, defaultValue, args);
    }
}
