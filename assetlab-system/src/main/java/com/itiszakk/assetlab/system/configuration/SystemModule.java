package com.itiszakk.assetlab.system.configuration;

import javax.inject.Singleton;

import com.itiszakk.assetlab.system.service.ModuleDefinition;
import com.itiszakk.assetlab.system.service.ModuleService;
import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.service.impl.ModuleServiceImpl;
import com.itiszakk.assetlab.system.service.impl.PropertyServiceImpl;
import com.itiszakk.assetlab.system.service.impl.TextServiceImpl;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface SystemModule {

    @Binds
    @IntoMap
    @StringKey(SystemModuleDefinition.MODULE_ID)
    ModuleDefinition bindSystemModuleDefinition(SystemModuleDefinition impl);

    @Binds
    @Singleton
    ModuleService bindModuleService(ModuleServiceImpl impl);

    @Binds
    @Singleton
    TextService bindTextService(TextServiceImpl impl);

    @Binds
    @Singleton
    PropertyService bindPropertyService(PropertyServiceImpl impl);
}
