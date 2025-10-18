package com.itiszakk.assetlab.system.service.impl;

import java.util.Comparator;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.ModuleService;
import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.service.TextService;

public class SystemInitializer {

    private final ModuleService moduleService;

    private final TextService textService;

    private final PropertyService propertyService;

    @Inject
    public SystemInitializer(ModuleService moduleService,
                             TextService textService,
                             PropertyService propertyService) {
        this.moduleService = moduleService;
        this.textService = textService;
        this.propertyService = propertyService;
    }

    public void initialize() {
        moduleService.getAllModules().stream()
                .sorted(Comparator.comparingInt(ModuleDefinition::getInitOrder))
                .forEach(this::initializeModule);
    }

    private void initializeModule(ModuleDefinition module) {
        textService.register(module.getTextBundle(), module.getClass().getClassLoader());
        module.getProperties().forEach(propertyService::register);
        module.start();
    }
}
