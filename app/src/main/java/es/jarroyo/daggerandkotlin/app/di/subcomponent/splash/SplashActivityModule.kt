package es.jarroyo.daggerandkotlin.app.di.subcomponent.splash

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.di.module.ActivityModule
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser.GetCurrentUserUseCase
import es.jarroyo.daggerandkotlin.ui.splash.activity.SplashActivity
import es.jarroyo.daggerandkotlin.ui.splash.activity.SplashView
import es.jarroyo.daggerandkotlin.ui.splash.presenter.SplashPresenter

@Module
class SplashActivityModule(activity: SplashActivity) : ActivityModule(activity) {

    @Provides
    fun provideLoginView(): SplashView = activity as SplashView


    @Provides
    fun provideSplashPresenter(view: SplashView, navigator: Navigator, getCurrentUserUseCase: GetCurrentUserUseCase)
            = SplashPresenter(view, navigator, getCurrentUserUseCase)
}