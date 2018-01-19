package es.jarroyo.daggerandkotlin.ui.login.activity

import android.view.View
import butterknife.OnClick
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.login.LoginActivityModule
import es.jarroyo.daggerandkotlin.ui.base.*
import es.jarroyo.daggerandkotlin.ui.login.presenter.LoginPresenter
import es.jarroyo.daggerandkotlin.ui.login.presenter.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.progress_bar_default.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter


    override var layoutId: Int = R.layout.activity_login

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(LoginActivityModule(this))
                .injectTo(this)

    }

    @OnClick(R.id.text_recover_password, R.id.text_sign_in, R.id.button_login)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.text_recover_password -> presenter.navigateToRecoverPassword()
            R.id.text_sign_in -> presenter.navigateToSignUp()
            R.id.button_login -> presenter.login(
                    input_email.text(),
                    input_password.text())
        }
    }


    /**
     *  LOGIN VIEW OVERRIDE
     */
    override fun showLoginLoading() {
        progress_wheel.visible()
        button_login.gone()
    }

    override fun hideLoginLoading() {
        progress_wheel.gone()
        button_login.visible()
    }

    override fun showIncorrectLoginUserDataError() {
        toast("showIncorrectLoginUserDataError")
    }

    override fun showIncorrectEmailFormatError() {
        toast("showIncorrectEmailFormatError")
    }

    override fun showIncorrectPasswordFormatError() {
        toast("showIncorrectPasswordFormatError")
    }
}
