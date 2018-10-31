package es.jarroyo.daggerandkotlin.ui

import android.support.multidex.MultiDexApplication
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.component.DaggerApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.module.ApplicationModule

/**
 * Created by javierarroyo on 28/12/17.
 */
class App: MultiDexApplication() {
    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}