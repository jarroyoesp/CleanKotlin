package es.jarroyo.daggerandkotlin.ui.home.activity

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.home.HomeActivityModule
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.base.toast
import es.jarroyo.daggerandkotlin.ui.home.HomeDisplayModel
import es.jarroyo.daggerandkotlin.ui.home.adapter.HomeAdapter
import es.jarroyo.daggerandkotlin.ui.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.progress_bar_default.*
import javax.inject.Inject

class HomeActivity: BaseActivity(), HomeView {

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
        configureHomeRecyclerView()
    }

    private fun configureHomeRecyclerView() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL)
        activity_home_rv.layoutManager = staggeredGridLayoutManager
    }

    override fun setDataInHomeList(homeListDisplayModel: HomeDisplayModel) {
        val homeAdapter = HomeAdapter(homeListDisplayModel, {
            presenter.onItemOnListClicked(it)
        })
        activity_home_rv.adapter = homeAdapter
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
        toast("Clicked")
    }
}
