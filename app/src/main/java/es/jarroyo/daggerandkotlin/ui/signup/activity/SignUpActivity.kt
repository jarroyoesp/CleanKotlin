package es.jarroyo.daggerandkotlin.ui.signup.activity

import android.os.Bundle
import butterknife.OnClick
import es.jarroyo.daggerandkotlin.BuildConfig
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.signup.SignUpActivityModule
import es.jarroyo.daggerandkotlin.ui.base.*
import es.jarroyo.daggerandkotlin.ui.signup.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.progress_bar_default.*
import javax.inject.Inject

class SignUpActivity: BaseActivity(), SignUpView {

    @Inject
    lateinit var presenter: SignUpPresenter

    override var layoutId: Int = R.layout.activity_signup

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SignUpActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OnClick(R.id.button_sign_in)
    fun onClick() {
        presenter.signUp(
                input_name.text(),
                input_surname.text(),
                input_email.text(),
                input_password.text(),
                input_repeat_password.text())
    }


    override fun showIncorrectNameFormatError() {
        toast(getString(R.string.sign_in_error_name_format, BuildConfig.MIN_NAME_LENGTH))
    }

    override fun showIncorrectSurnameFormatError() {
        toast(getString(R.string.sign_in_error_surname_format, BuildConfig.MIN_SURNAME_LENGTH))
    }

    override fun showIncorrectEmailFormatError() {
        toast(getString(R.string.sign_in_error_email_format))
    }

    override fun showIncorrectPasswordFormatError() {
        toast(getString(R.string.sign_in_error_password_format))
    }

    override fun showUserAlreadyExistsError() {
        toast(getString(R.string.sign_in_error_user_already_exists))
    }

    override fun showIncorrectMatchingPasswordsError() {
        toast(getString(R.string.sign_in_error_password_not_match))
    }

    override fun showSignUpLoading() {
        progress_wheel.visible()
        button_sign_in.gone()
    }

    override fun hideSignUpLoading() {
        progress_wheel.gone()
        button_sign_in.visible()
    }

}
