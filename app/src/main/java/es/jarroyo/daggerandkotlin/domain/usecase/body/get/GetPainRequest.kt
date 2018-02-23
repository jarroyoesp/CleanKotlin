package es.jarroyo.daggerandkotlin.domain.usecase.body.get

import es.jarroyo.daggerandkotlin.domain.usecase.base.AuthBaseRequest


class GetPainRequest: AuthBaseRequest {
    override var userId: String? = null

    override fun validate(): Boolean {
        return true
    }
}