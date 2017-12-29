package es.jarroyo.daggerandkotlin.ui.login.presenter

import es.jarroyo.daggerandkotlin.ui.base.PresentationView

/**
 * Created by javierarroyo on 28/12/17.
 */
interface LoginView: PresentationView {

    fun showLoginLoading()

    fun hideLoginLoading()

    fun showIncorrectLoginUserDataError()

    fun showIncorrectEmailFormatError()

    fun showIncorrectPasswordFormatError()
}