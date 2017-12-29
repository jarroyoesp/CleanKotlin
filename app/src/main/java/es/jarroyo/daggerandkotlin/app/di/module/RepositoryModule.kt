package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun provideUseRepository(networkDataSource: NetworkDataSource,
                             userEntityDataMapper: UserEntityDataMapper)
            = UserRepository(networkDataSource, userEntityDataMapper)
}