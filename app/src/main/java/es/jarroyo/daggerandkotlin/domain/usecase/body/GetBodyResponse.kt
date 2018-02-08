package es.jarroyo.daggerandkotlin.domain.usecase.body

import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse

/**
 * Created by javierarroyo on 7/2/18.
 */
interface GetBodyResponse  : BaseResponse {

    fun onBodyPartsReceived(comics: List<DataHome>)

}