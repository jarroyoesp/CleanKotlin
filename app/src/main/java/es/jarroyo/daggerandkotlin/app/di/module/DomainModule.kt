package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginUseCase

@Module
class DomainModule {

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository, executor: UseCaseExecutor,
                            mainThread: MainThread)
            = LoginUseCase(userRepository, executor, mainThread)


}