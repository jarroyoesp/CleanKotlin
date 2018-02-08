package es.jarroyo.daggerandkotlin.ui.body.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import butterknife.OnClick
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.ui.body.BodyPartDisplayModel
import kotlinx.android.synthetic.main.fragment_bottomsheet_select_pain.*

class BottomSheetPainFragment() : BottomSheetDialogFragment() {

    companion object{
        private val ARGS_EXTRA_BODYPART = "ARGS_EXTRA_BODYPART"

        fun newInstance(bodyPart: BodyPartDisplayModel?):BottomSheetPainFragment{
            val args: Bundle = Bundle()
            args.putSerializable(ARGS_EXTRA_BODYPART, bodyPart)
            val fragment = BottomSheetPainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mBodyPart: BodyPartDisplayModel

    // View elements
    private lateinit var mTvTitle: TextView
    private lateinit var mTvPainLevel: TextView
    private lateinit var mSeekBar: SeekBar
    private lateinit var mButtonSave: Button
    private lateinit var mOnClickSavePain: OnClickSavePain


    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_bottomsheet_select_pain, null)
        dialog.setContentView(contentView)

        if (!(activity is OnClickSavePain)) {
            throw throw IllegalArgumentException("Activity has to implement interface OnClickSavePain")
        }

        // VIEW
        mTvTitle = contentView.findViewById(R.id.fragmentSelectPainTvTitle)
        mSeekBar = contentView.findViewById(R.id.fragmentSelectPainseekBar)
        mButtonSave = contentView.findViewById(R.id.fragmentSelectPainButtonSave)
        mTvPainLevel = contentView.findViewById(R.id.fragmentSelectPainTvPainLevel)

        getArgs()
        configView()
    }

    private fun getArgs() {
        mBodyPart = this.arguments.getSerializable(ARGS_EXTRA_BODYPART) as BodyPartDisplayModel
    }

    private fun configView() {
        mOnClickSavePain = activity as OnClickSavePain
        mTvTitle.text = mBodyPart.nameBodyPart
        mTvPainLevel.text = mBodyPart.painLevel.toString()
        mSeekBar.progress = mBodyPart.painLevel

        mButtonSave.setOnClickListener { v -> savePainLevel() }
        
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                mBodyPart.painLevel = progress
                mTvPainLevel.text = progress.toString()
            }
        })
    }

    private fun savePainLevel() {
        this.dismiss()
        mOnClickSavePain.savePain(mBodyPart)
    }

    /**
     * INTERFACE SAVE PAIN LEVEL
     */
    interface OnClickSavePain{
        fun savePain(bodyPart: BodyPartDisplayModel)
    }
}
