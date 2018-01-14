package es.jarroyo.daggerandkotlin.ui.home.activity

import android.os.Bundle
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.home.HomeActivityModule
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.base.toast
import es.jarroyo.daggerandkotlin.ui.home.HomeDisplayModel
import es.jarroyo.daggerandkotlin.ui.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.progress_bar_default.*
import javax.inject.Inject

class HomeActivity: BaseActivity(), HomeView {
    override fun setDataInHomeList(homeListDisplayModel: HomeDisplayModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressWheel() {
        progress_wheel.spin()
    }

    override fun hideProgressWheel() {
        progress_wheel.stopSpinning()
    }

    override fun showHomeNotFoundError() {
        toast(getString(R.string.error_character_comics_not_found))
    }

    override fun showNetworkConnectionError() {
        toast(getString(R.string.error_network_connection))
    }

    override fun showHomeHasNotAllInfoError() {
        toast(getString(R.string.error_comic_not_valid))
    }

    @Inject
    lateinit var presenter: HomePresenter

    override var layoutId: Int = R.layout.activity_home

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HomeActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getHome()
    }
}
