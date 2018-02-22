package es.jarroyo.daggerandkotlin.domain.usecase.body.save

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse

/**
 * Created by javierarroyo on 7/2/18.
 */
interface SavePainResponse : BaseResponse {

    fun onSavePainReceived(bodyPartList: List<BodyPart>)

}