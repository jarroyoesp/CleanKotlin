package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.mapper.HomeEntityDataMapper
import es.jarroyo.daggerandkotlin.data.mapper.NetworkGetHomeResponseToHomeEntityMapper
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(networkClientManager: NetworkClientManager, networkGetHomeResponseToHomeEntityMapper: NetworkGetHomeResponseToHomeEntityMapper)
            = NetworkDataSource(networkClientManager, networkGetHomeResponseToHomeEntityMapper)

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

}