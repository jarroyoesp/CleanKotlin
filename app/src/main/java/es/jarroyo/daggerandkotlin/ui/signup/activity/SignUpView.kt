package es.jarroyo.daggerandkotlin.ui.signup.activity

import es.jarroyo.daggerandkotlin.ui.base.PresentationView

/**
 * Created by javierarroyo on 28/12/17.
 */
interface SignUpView : PresentationView {

    fun showIncorrectNameFormatError()

    fun showIncorrectSurnameFormatError()

    fun showIncorrectEmailFormatError()

    fun showIncorrectPasswordFormatError()

    fun showUserAlreadyExistsError()

    fun showIncorrectMatchingPasswordsError()

    fun showSignUpLoading()

    fun hideSignUpLoading()
}