package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.mapper.HomeEntityDataMapper
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.repository.BodyPartRepository
import es.jarroyo.daggerandkotlin.data.repository.HomeRepository
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.data.source.cache.CacheDataSource
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun provideUseRepository(networkDataSource: NetworkDataSource,
                             cacheDataSource: CacheDataSource,
                             userEntityDataMapper: UserEntityDataMapper)
            = UserRepository(networkDataSource, cacheDataSource, userEntityDataMapper)

    @Provides
    @Singleton
    fun provideHomeRepository(networkDataSource: NetworkDataSource,  homeEntityDataMapper: HomeEntityDataMapper)
            = HomeRepository(networkDataSource, homeEntityDataMapper)


    @Provides
    @Singleton
    fun provideBodyPartRepository(networkDataSource: NetworkDataSource, cacheDataSource: CacheDataSource)
            = BodyPartRepository(networkDataSource, cacheDataSource)
}