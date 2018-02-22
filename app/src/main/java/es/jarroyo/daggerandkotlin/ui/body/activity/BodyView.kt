package es.jarroyo.daggerandkotlin.ui.body.activity

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.ui.base.PresentationView

/**
 * Created by javierarroyo on 7/2/18.
 */
interface BodyView: PresentationView {
    fun onPainBodyPartsReceived(painBodyList: List<BodyPart>)

    fun onSavePain()

    fun showBodyLoading()

    fun hideBodyLoading()
}