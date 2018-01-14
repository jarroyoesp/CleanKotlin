package es.jarroyo.daggerandkotlin.domain.usecase.home

import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse


interface GetHomeResponse : BaseResponse {

    fun onDataHomeReceived(comics: List<DataHome>)
}