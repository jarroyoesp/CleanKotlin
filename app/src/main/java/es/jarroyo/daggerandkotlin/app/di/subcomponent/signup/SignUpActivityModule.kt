package es.jarroyo.daggerandkotlin.app.di.subcomponent.signup

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.di.module.ActivityModule
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpUseCase
import es.jarroyo.daggerandkotlin.ui.signup.activity.SignUpActivity
import es.jarroyo.daggerandkotlin.ui.signup.activity.SignUpView
import es.jarroyo.daggerandkotlin.ui.signup.presenter.SignUpPresenter

/**
 * Created by javierarroyo on 24/1/18.
 */
@Module
class SignUpActivityModule(activity: SignUpActivity) : ActivityModule(activity) {

    @Provides
    fun provideLoginView(): SignUpView = activity as SignUpView


    @Provides
    fun provideSignUpPresenter(view: SignUpView, navigator: Navigator, signUpUseCase: SignUpUseCase) = SignUpPresenter(view, navigator, signUpUseCase)
}