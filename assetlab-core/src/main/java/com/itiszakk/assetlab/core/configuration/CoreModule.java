package com.itiszakk.assetlab.core.configuration;

import javax.inject.Singleton;

import com.itiszakk.assetlab.core.module.CoreModuleDefinition;
import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.service.TagService;
import com.itiszakk.assetlab.core.service.impl.AssetMetadataServiceImpl;
import com.itiszakk.assetlab.core.service.impl.AssetServiceImpl;
import com.itiszakk.assetlab.core.service.impl.TagServiceImpl;
import com.itiszakk.assetlab.system.service.ModuleDefinition;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface CoreModule {

    @Binds
    @IntoMap
    @StringKey(CoreModuleDefinition.MODULE_ID)
    ModuleDefinition bindCoreModuleDefinition(CoreModuleDefinition moduleDefinition);

    @Binds
    @Singleton
    AssetService bindAssetService(AssetServiceImpl impl);

    @Binds
    @Singleton
    AssetMetadataService bindAssetMetadataService(AssetMetadataServiceImpl impl);

    @Binds
    @Singleton
    TagService bindTagService(TagServiceImpl impl);
}
