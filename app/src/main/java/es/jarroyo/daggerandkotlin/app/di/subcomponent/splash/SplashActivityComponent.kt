package es.jarroyo.daggerandkotlin.app.di.subcomponent.splash

import dagger.Subcomponent
import es.jarroyo.daggerandkotlin.ui.splash.activity.SplashActivity

@Subcomponent(modules = arrayOf(SplashActivityModule::class))
interface SplashActivityComponent {
    fun injectTo(activity: SplashActivity)
}