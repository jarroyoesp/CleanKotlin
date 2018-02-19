package es.jarroyo.daggerandkotlin.ui.body.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import butterknife.OnClick
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.di.subcomponent.body.BodyActivityModule
import es.jarroyo.daggerandkotlin.ui.base.BaseActivity
import es.jarroyo.daggerandkotlin.ui.body.BodyPartDisplayModel
import es.jarroyo.daggerandkotlin.ui.body.fragment.BottomSheetPainFragment
import es.jarroyo.daggerandkotlin.ui.body.presenter.BodyPresenter
import javax.inject.Inject


class BodyActivity : BaseActivity(), BodyView, BottomSheetPainFragment.OnClickSavePain {



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
        var textBodyPart: String? = view?.tag?.toString()

        var bodyPart = BodyPartDisplayModel(1, textBodyPart, 1)
        val bottomSheetDialogFragment = BottomSheetPainFragment.newInstance(bodyPart)
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.getTag())
    }

    override fun savePain(bodyPart: BodyPartDisplayModel) {
        showPainInBodyPart(bodyPart)
    }

    /**
     * Show In View - BodyPart the pain in this part
     */
    fun showPainInBodyPart(bodyPart: BodyPartDisplayModel) {
        var buttonBodyPart: Button = findViewById<Button>(findBodypartView(bodyPart))
        buttonBodyPart?.text = bodyPart.painLevel.toString()
        buttonBodyPart.visibility = View.VISIBLE

        var drawable = ContextCompat.getDrawable(this, R.drawable.body_circle_shape_colored)
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.colorAccent))
        buttonBodyPart?.background = drawable


        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 1000
        fadeInAnimation.fillAfter = true
        buttonBodyPart.startAnimation(fadeInAnimation)
    }

    fun findBodypartView(bodyPart: BodyPartDisplayModel): Int {
        when(bodyPart.nameBodyPart) {
            getString(R.string.right_hip_key) -> return R.id.right_hip
            getString(R.string.left_hip_key) -> return R.id.left_hip
            getString(R.string.lumbar_spine_key) -> return R.id.lumbar_spine
            getString(R.string.left_groin_key) -> return R.id.left_groin
            getString(R.string.right_groin_key) -> return R.id.right_groin
            getString(R.string.right_elbow_key) -> return R.id.right_elbow
            getString(R.string.left_elbow_key) -> return R.id.left_elbow
            getString(R.string.right_jaw_key) -> return R.id.right_jaw
            getString(R.string.left_jaw_key) -> return R.id.left_jaw
            getString(R.string.cervical_spine_key) -> return R.id.cervical_spine
            getString(R.string.lumbar_spine_key) -> return R.id.lumbar_spine
            getString(R.string.thoratic_spine_key) -> return R.id.thoratic_spine
            getString(R.string.right_knee_key) -> return R.id.right_knee
            getString(R.string.left_knee_key) -> return R.id.left_knee
            getString(R.string.right_shoulder_key) -> return R.id.right_shoulder
            getString(R.string.left_shoulder_key) -> return R.id.left_shoulder
        }
        return -1
    }

}
