package com.itiszakk.assetlab.system.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.util.ResourceUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class TextServiceImpl implements TextService {

    private final List<ResourceBundle> bundles = new ArrayList<>();

    @Override
    public void register(String bundle, ClassLoader classLoader) {
        if (StringUtils.isNotEmpty(bundle)) {
            bundles.add(ResourceUtils.loadBundle(bundle, classLoader));
        }
    }

    @Override
    public String getText(String code, Object... args) {
        String message = getMessageFromBundles(code);
        return StringUtils.isNotEmpty(message)
                ? MessageFormat.format(message, args)
                : null;
    }

    private String getMessageFromBundles(String code) {

        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (ResourceBundle bundle : bundles) {
            if (bundle.containsKey(code)) {
                return bundle.getString(code);
            }
        }

        return code;
    }
}