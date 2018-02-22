package es.jarroyo.daggerandkotlin.app.navigator

import android.content.Intent
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyActivity
import es.jarroyo.daggerandkotlin.ui.login.activity.LoginActivity
import es.jarroyo.daggerandkotlin.ui.signup.activity.SignUpActivity

class Navigator {

    var currentActivity: BaseActivity? = null

    private fun toDefaultActivity(activity: Class<*>) {
        val intent = Intent(currentActivity, activity)
        currentActivity?.startActivity(intent)
    }

    private fun toDefaultActivityForResult(requestCode: Int, activity: Class<*>) {
        val intent = Intent(currentActivity, activity)
        currentActivity?.startActivityForResult(intent, requestCode)
    }

    private fun toDefaultActivityCleaningStack(activity: Class<*>) {
        val intent = Intent(currentActivity, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        currentActivity?.startActivity(intent)
    }

    fun toSplash() {
        //toDefaultActivityCleaningStack(SplashActivity::class.java)
    }

    fun toLogin() {
        toDefaultActivity(LoginActivity::class.java)
    }

    fun toMain() {
        toDefaultActivityCleaningStack(BodyActivity::class.java)
    }

    fun toSignUp() {
        toDefaultActivity(SignUpActivity::class.java)
    }

    fun toRecoverPassword() {
        //toDefaultActivity(RecoverPasswordActivity::class.java)
    }

    fun toLoginCleaningStack() {
        toDefaultActivityCleaningStack(LoginActivity::class.java)
    }

    fun finishActivity() {
        currentActivity?.finish()
    }
}
