package es.jarroyo.daggerandkotlin.domain.usecase.body.get

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse

/**
 * Created by javierarroyo on 7/2/18.
 */
interface GetPainResponse : BaseResponse {

    fun onPainListReceived(bodyPartList: List<BodyPart>)

}