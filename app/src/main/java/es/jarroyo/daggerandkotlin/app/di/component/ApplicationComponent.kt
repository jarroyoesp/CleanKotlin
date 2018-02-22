package es.jarroyo.daggerandkotlin.app.di.component

import dagger.Component
import es.jarroyo.daggerandkotlin.app.di.module.ApplicationModule
import es.jarroyo.daggerandkotlin.app.di.module.DataModule
import es.jarroyo.daggerandkotlin.app.di.module.DomainModule
import es.jarroyo.daggerandkotlin.app.di.module.RepositoryModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.body.BodyActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.body.BodyActivityModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.home.HomeActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.home.HomeActivityModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.login.LoginActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.login.LoginActivityModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.signup.SignUpActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.signup.SignUpActivityModule
import es.jarroyo.daggerandkotlin.app.di.subcomponent.splash.SplashActivityComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.splash.SplashActivityModule
import javax.inject.Singleton

/**
 * Created by javierarroyo on 28/12/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        DomainModule::class,
        DataModule::class,
        RepositoryModule::class)
)
interface ApplicationComponent {
    fun plus(module: LoginActivityModule): LoginActivityComponent
    fun plus(module: HomeActivityModule): HomeActivityComponent
    fun plus(module: SignUpActivityModule): SignUpActivityComponent
    fun plus(module: BodyActivityModule): BodyActivityComponent
    fun plus(module: SplashActivityModule): SplashActivityComponent
}