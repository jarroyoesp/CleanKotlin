package es.jarroyo.daggerandkotlin.domain.usecase.body.get

import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class GetPainRequest(var userId: String = ""): BaseRequest {

    override fun validate(): Boolean {
        return true
    }
}