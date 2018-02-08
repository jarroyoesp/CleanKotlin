package es.jarroyo.daggerandkotlin.ui.body.activity

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.body.BodyActivityModule
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.base.toast
import es.jarroyo.daggerandkotlin.ui.body.presenter.BodyPresenter
import javax.inject.Inject

class BodyActivity : BaseActivity(), BodyView {


    override var layoutId: Int = R.layout.activity_body

    @Inject
    lateinit var presenter: BodyPresenter

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BodyActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setBodyParts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBodyLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideBodyLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * ON CLICKS
     */
    @OnClick(R.id.right_hand,
             R.id.left_hand,
             R.id.right_foot,
             R.id.left_foot,
             R.id.left_hip,
             R.id.right_hip,
             R.id.left_groin,
             R.id.right_groin,
             R.id.right_elbow,
             R.id.left_elbow,
             R.id.right_shoulder,
             R.id.left_shoulder,
             R.id.right_jaw,
             R.id.left_jaw,
             R.id.cervical_spine,
             R.id.thoratic_spine,
             R.id.lumbar_spine,
             R.id.right_knee,
             R.id.left_knee)
    fun onViewClicked(view: View) {
        var text: String? = view?.tag?.toString()
        toast(text)
        when (view.id) {


            /*R.id.text_recover_password -> presenter.navigateToRecoverPassword()
            R.id.text_sign_in -> presenter.navigateToSignUp()
            R.id.button_login -> presenter.login(
                    input_email.text(),
                    input_password.text())*/
        }
    }

}
