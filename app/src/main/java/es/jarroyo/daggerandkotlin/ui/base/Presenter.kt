package es.jarroyo.daggerandkotlin.ui.base

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse

interface Presenter<out T : PresentationView> :
        BaseResponse {

    val view: T
    val navigator: Navigator

    fun clearView()

    override fun onNetworkConnectionError() {
        clearView()
    }

    override fun onNetworkServiceError() {
        clearView()
    }

    override fun onUnknownError() {
        clearView()
    }

    override fun onUserIsNotLoggedIn() {
        navigator.toLoginCleaningStack()
    }
}