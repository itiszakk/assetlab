package com.itiszakk.assetlab.system.util;

import java.util.Objects;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.ModuleService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModuleUtils {

    private static final String APPLICATION_MODULE_PREFIX = "com.itiszakk.assetlab";

    private static ModuleService moduleService;

    public static void init(ModuleService moduleService) {
        ModuleUtils.moduleService = moduleService;
    }

    public static ModuleDefinition getCurrentModule() {

        Objects.requireNonNull(moduleService, "ModuleService not initialized");

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        ModuleDefinition module = null;

        for (StackTraceElement element : stackTrace) {
            if (isApplicationTraceElement(element)) {
                module = findModule(element);
            }
        }

        Objects.requireNonNull(module, "Current module not found");

        return module;
    }

    private static boolean isApplicationTraceElement(StackTraceElement element) {
        return element.getClassName().startsWith(APPLICATION_MODULE_PREFIX);
    }

    private static ModuleDefinition findModule(StackTraceElement element) {
        return moduleService.getAllModules().stream()
                .filter(module -> element.getClassName().startsWith(module.getId()))
                .findFirst()
                .orElse(null);
    }
}
