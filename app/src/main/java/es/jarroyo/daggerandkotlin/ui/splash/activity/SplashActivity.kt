package es.jarroyo.daggerandkotlin.ui.splash.activity

import android.os.Bundle
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.splash.SplashActivityModule
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.splash.presenter.SplashPresenter
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter


    override var layoutId: Int = R.layout.activity_splash

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SplashActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadInitialData()
    }
}
