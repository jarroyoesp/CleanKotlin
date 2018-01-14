package es.jarroyo.daggerandkotlin.app.di.subcomponent.home

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.di.module.ActivityModule
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeUseCase
import es.jarroyo.daggerandkotlin.ui.home.activity.HomeActivity
import es.jarroyo.daggerandkotlin.ui.home.activity.HomeView
import es.jarroyo.daggerandkotlin.ui.home.presenter.HomePresenter

@Module
class HomeActivityModule(activity: HomeActivity) :ActivityModule(activity) {

    @Provides
    fun provideHomeView(): HomeView = activity as HomeView

    @Provides
    fun provideHomePresenter(view: HomeView, navigator: Navigator, homeUseCase: GetHomeUseCase)
            = HomePresenter(view, navigator, homeUseCase)

}