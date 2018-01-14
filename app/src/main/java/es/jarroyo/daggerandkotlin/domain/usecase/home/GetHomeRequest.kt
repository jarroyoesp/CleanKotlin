package es.jarroyo.daggerandkotlin.domain.usecase.home

import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class GetHomeRequest(val characterId: Int, val maxItems: Int): BaseRequest {

    override fun validate() = true
}