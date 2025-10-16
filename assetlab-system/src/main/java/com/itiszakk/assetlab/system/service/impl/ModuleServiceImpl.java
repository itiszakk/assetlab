package com.itiszakk.assetlab.system.service.impl;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {

    private final Map<String, ModuleDefinition> modules;

    @Inject
    public ModuleServiceImpl(Map<String, ModuleDefinition> modules) {
        this.modules = modules;
    }

    @Override
    public ModuleDefinition getModule(String id) {
        return modules.get(id);
    }

    @Override
    public Collection<ModuleDefinition> getAllModules() {
        return modules.values();
    }
}
