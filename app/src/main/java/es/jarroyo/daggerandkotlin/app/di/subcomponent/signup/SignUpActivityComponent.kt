package es.jarroyo.daggerandkotlin.app.di.subcomponent.signup

import dagger.Subcomponent
import es.jarroyo.daggerandkotlin.ui.signup.activity.SignUpActivity

/**
 * Created by javierarroyo on 24/1/18.
 */
@Subcomponent(modules = arrayOf(SignUpActivityModule::class))
interface SignUpActivityComponent {
    fun injectTo(activity: SignUpActivity)
}