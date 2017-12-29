package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideNetworkDataSource()
            = NetworkDataSource()

    @Provides
    @Singleton
    fun provideUserEntityDataMapper()
            = UserEntityDataMapper()
}