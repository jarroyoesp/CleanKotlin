package es.jarroyo.daggerandkotlin.app.di.subcomponent.home

import dagger.Subcomponent
import es.jarroyo.daggerandkotlin.ui.home.activity.HomeActivity

@Subcomponent(modules = arrayOf(HomeActivityModule::class))
interface HomeActivityComponent {
    fun injectTo(activity: HomeActivity)
}