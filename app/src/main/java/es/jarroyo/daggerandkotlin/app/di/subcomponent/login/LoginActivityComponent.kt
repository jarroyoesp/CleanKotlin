package es.jarroyo.daggerandkotlin.app.di.subcomponent.login

import dagger.Subcomponent
import es.jarroyo.daggerandkotlin.ui.login.activity.LoginActivity

@Subcomponent(modules = arrayOf(LoginActivityModule::class))
interface LoginActivityComponent {
    fun injectTo(activity: LoginActivity)
}