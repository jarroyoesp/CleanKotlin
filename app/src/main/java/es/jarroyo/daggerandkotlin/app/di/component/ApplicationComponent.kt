package es.jarroyo.daggerandkotlin.app.di.component

import dagger.Component
import es.jarroyo.daggerandkotlin.app.di.module.ApplicationModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.login.LoginActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.login.LoginActivityModule
import javax.inject.Singleton

/**
 * Created by javierarroyo on 28/12/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    //fun inject(app: App)
    fun plus(module: LoginActivityModule): LoginActivityComponent
}