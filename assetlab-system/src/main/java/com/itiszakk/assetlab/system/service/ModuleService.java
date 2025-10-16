package com.itiszakk.assetlab.system.service;

import java.util.Collection;

public interface ModuleService {

    ModuleDefinition getModule(String id);

    Collection<ModuleDefinition> getAllModules();
}
