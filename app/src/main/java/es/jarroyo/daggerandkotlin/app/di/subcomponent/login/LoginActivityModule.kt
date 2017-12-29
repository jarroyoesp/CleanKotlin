package es.jarroyo.daggerandkotlin.app.di.subcomponent.login

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.di.module.ActivityModule
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginUseCase
import es.jarroyo.daggerandkotlin.ui.login.activity.LoginActivity
import es.jarroyo.daggerandkotlin.ui.login.presenter.LoginPresenter
import es.jarroyo.daggerandkotlin.ui.login.presenter.LoginView

@Module
class LoginActivityModule(activity: LoginActivity) : ActivityModule(activity) {

    @Provides
    fun provideLoginView(): LoginView = activity as LoginView


    @Provides
    fun provideLoginPresenter(view: LoginView, navigator: Navigator, loginUseCase: LoginUseCase)
            = LoginPresenter(view, navigator, loginUseCase)
}