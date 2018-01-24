package es.jarroyo.daggerandkotlin.ui.signup.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpRequest
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpResponse
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpUseCase
import es.jarroyo.daggerandkotlin.ui.base.Presenter
import es.jarroyo.daggerandkotlin.ui.signup.activity.SignUpView


class SignUpPresenter(override val view: SignUpView,
                      override val navigator: Navigator,
                      var signUpUseCase: SignUpUseCase) :
        Presenter<SignUpView> , SignUpRequest.Validator, SignUpResponse {
    fun signUp(name: String, surname: String, email: String, password: String,
               repeatedPassword: String) {
        view.showSignUpLoading()

        val request = SignUpRequest(name, surname, email, password, repeatedPassword, this)
        signUpUseCase.execute(request, this)
    }

    override fun clearView() {
        view.hideSignUpLoading()
    }

    /**
     * SIGNUP REQUEST VALIDATOR
     */
    override fun onIncorrectEmailFormat() {
        view.showIncorrectEmailFormatError()
        clearView()
    }

    override fun onIncorrectPasswordFormat() {
        view.showIncorrectPasswordFormatError()
        clearView()
    }


    override fun onUserSignedIn(user: User) {
        navigator.toMain()
    }

    override fun onUserAlreadyExists() {
        view.showUserAlreadyExistsError()
        clearView()
    }

    override fun onIncorrectNameFormat() {
        view.showIncorrectNameFormatError()
        clearView()
    }

    override fun onIncorrectSurnameFormat() {
        view.showIncorrectSurnameFormatError()
        clearView()
    }

    override fun onIncorrectMatchingPasswords() {
        view.showIncorrectMatchingPasswordsError()
        clearView()
    }



}
