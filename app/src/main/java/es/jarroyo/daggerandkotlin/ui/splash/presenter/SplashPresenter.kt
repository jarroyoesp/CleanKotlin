package es.jarroyo.daggerandkotlin.ui.splash.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser.GetCurrentUserResponse
import es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser.GetCurrentUserUseCase
import es.jarroyo.daggerandkotlin.ui.base.Presenter
import es.jarroyo.daggerandkotlin.ui.splash.activity.SplashView
import java.util.*
import kotlin.concurrent.schedule


class SplashPresenter(override val view: SplashView,
                      override val navigator: Navigator,
                      val getCurrentUserUseCase: GetCurrentUserUseCase) :
        Presenter<SplashView>, GetCurrentUserResponse {

    private val COUNT_DOWN_TIME: Long = 2000

    fun loadInitialData() = Timer("SettingUp", false).schedule(COUNT_DOWN_TIME) {
        getCurrentUserUseCase.execute(response = this@SplashPresenter)
    }

    override fun onCurrentUserReceived(user: User) {
        navigator.toMain()
    }

    override fun onUserIsNotLoggedIn() {
        super.onUserIsNotLoggedIn()
        navigator.toLoginCleaningStack()
    }
    override fun clearView() {}
}