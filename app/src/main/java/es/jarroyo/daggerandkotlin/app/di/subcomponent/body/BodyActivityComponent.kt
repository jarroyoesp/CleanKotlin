package es.jarroyo.daggerandkotlin.app.di.subcomponent.body

import dagger.Subcomponent
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyActivity

@Subcomponent(modules = arrayOf(BodyActivityModule::class))
interface BodyActivityComponent {
    fun injectTo(activity: BodyActivity)
}