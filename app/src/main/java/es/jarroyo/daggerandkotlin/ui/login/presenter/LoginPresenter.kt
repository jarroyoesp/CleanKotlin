package es.jarroyo.daggerandkotlin.ui.login.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginResponse
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginUseCase
import es.jarroyo.daggerandkotlin.ui.base.Presenter


class LoginPresenter (override val view: LoginView,
                      override val navigator: Navigator,
                      var loginUseCase: LoginUseCase) :
        Presenter<LoginView> , LoginRequest.Validator, LoginResponse {
    fun login(email: String, password: String) {
        view.showLoginLoading()

        val request = LoginRequest(email, password, this)
        loginUseCase.execute(request, this)
    }

    fun navigateToSignUp() = navigator.toSignUp()

    fun navigateToRecoverPassword() = navigator.toRecoverPassword()

    override fun clearView() {

    }

    /**
     * LOGIN REQUEST VALIDATOR
     */
    override fun onIncorrectEmailFormat() {
        view.showIncorrectLoginUserDataError()
        clearView() //To change body of created functions use File | Settings | File Templates.
    }


    override fun onIncorrectPasswordFormat() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * LOGIN RESPONSE OVERRIDE
     */
    override fun onUserLoggedIn(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIncorrectLoginUserData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
