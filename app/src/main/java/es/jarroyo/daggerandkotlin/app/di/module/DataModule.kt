package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.mapper.HomeEntityDataMapper
import es.jarroyo.daggerandkotlin.data.mapper.NetworkAuthenticationResponseToUserEntityMapper
import es.jarroyo.daggerandkotlin.data.mapper.NetworkGetHomeResponseToHomeEntityMapper
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.source.cache.CacheDataSource
import es.jarroyo.daggerandkotlin.data.source.disk.DiskDataSource
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.ui.App
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(networkClientManager: NetworkClientManager,
                                 networkAuthenticationResponseToUserEntityMapper: NetworkAuthenticationResponseToUserEntityMapper,
                                 networkGetHomeResponseToHomeEntityMapper: NetworkGetHomeResponseToHomeEntityMapper)
            = NetworkDataSource(networkClientManager, networkAuthenticationResponseToUserEntityMapper, networkGetHomeResponseToHomeEntityMapper)

    @Provides
    @Singleton
    fun provideCacheDataSource()
            = CacheDataSource()

    @Provides
    @Singleton
    fun provideDiskDataSource(appContext: App)
            = DiskDataSource(appContext)

    @Provides
    @Singleton
    fun provideNetworkClientManager(/*diskDataSource: DiskDataSource*/)
            = NetworkClientManager(/*diskDataSource*/)

    @Provides
    @Singleton
    fun provideUserEntityDataMapper()
            = UserEntityDataMapper()

    @Provides
    @Singleton
    fun provideNetworkGetHomeResponseToHomeEntityMapper()
            = NetworkGetHomeResponseToHomeEntityMapper()

    @Provides
    @Singleton
    fun provideHomeEntityDataMapper()
            = HomeEntityDataMapper()

    @Provides
    @Singleton
    fun provideNetworkAuthenticationResponseToUserEntityMapper()
            = NetworkAuthenticationResponseToUserEntityMapper()

}