package es.jarroyo.daggerandkotlin.ui.login.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.ui.base.Presenter


class LoginPresenter (override val view: LoginView,
                      override val navigator: Navigator) :
        Presenter<LoginView> {

    fun login(email: String, password: String) {
        view.showLoginLoading()

        /*val request = LoginRequest(email, password, this)
        loginUseCase.execute(request, this)*/
    }

    fun navigateToSignUp() = navigator.toSignUp()
    fun navigateToRecoverPassword() = navigator.toRecoverPassword()


    override fun clearView() {

    }
}