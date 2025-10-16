package com.itiszakk.assetlab.system.util;

import com.itiszakk.assetlab.system.service.TextService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextUtils {

    private static TextService textService;

    public static void init(TextService textService) {
        TextUtils.textService = textService;
    }

    public static String getText(String code) {
        return textService.getText(code);
    }

    public static String getText(String code, Object... args) {
        return textService.getText(code, args);
    }
}
