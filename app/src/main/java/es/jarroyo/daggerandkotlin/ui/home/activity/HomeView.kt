package es.jarroyo.daggerandkotlin.ui.home.activity

import es.jarroyo.daggerandkotlin.ui.base.PresentationView
import es.jarroyo.daggerandkotlin.ui.home.HomeDisplayModel

/**
 * Created by javierarroyo on 29/12/17.
 */
interface HomeView: PresentationView {
    fun setDataInHomeList(homeListDisplayModel: HomeDisplayModel)

    fun showProgressWheel()

    fun hideProgressWheel()

    fun showHomeNotFoundError()

    fun showNetworkConnectionError()

    fun showHomeHasNotAllInfoError()


}