package com.itiszakk.assetlab.system.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.util.ModuleUtils;
import com.itiszakk.assetlab.system.util.ResourceUtils;

public class TextServiceImpl implements TextService {

    private final Map<String, String> bundles = new HashMap<>();

    @Inject
    public TextServiceImpl() {

    }

    @Override
    public void registerBundle(String moduleId, String bundle) {
        bundles.put(moduleId, bundle);
    }

    @Override
    public String getText(String code, Object... args) {

        if (StringUtils.isEmpty(code)) {
            return null;
        }

        ModuleDefinition module = ModuleUtils.getCurrentModule();
        String bundleName = bundles.get(module.getId());
        if (bundleName == null) {
            String message = MessageFormat.format("Cannot find resource bundle for module [{0}]", module.getId());
            throw new RuntimeException(message);
        }

        ClassLoader classLoader = module.getClass().getClassLoader();
        ResourceBundle bundle = ResourceUtils.loadBundle(bundleName, classLoader);
        String message = bundle.getString(code);

        return StringUtils.isNotEmpty(message)
                ? MessageFormat.format(message, args)
                : null;
    }
}
