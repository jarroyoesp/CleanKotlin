package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.data.repository.HomeRepository
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpUseCase

@Module
class DomainModule {

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository, executor: UseCaseExecutor,
                            mainThread: MainThread)
            = LoginUseCase(userRepository, executor, mainThread)


    @Provides
    fun provideGetHomeUseCase(homeRepository: HomeRepository, executor: UseCaseExecutor,
                                mainThread: MainThread)
            = GetHomeUseCase(homeRepository, executor, mainThread)

    @Provides
    fun provideSignUpUseCase(userRepository: UserRepository, executor: UseCaseExecutor,
                              mainThread: MainThread)
            = SignUpUseCase(userRepository, executor, mainThread)
}