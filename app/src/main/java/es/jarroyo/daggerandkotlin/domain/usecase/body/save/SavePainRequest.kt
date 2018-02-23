package es.jarroyo.daggerandkotlin.domain.usecase.body.save

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.AuthBaseRequest


class SavePainRequest(var bodypart: BodyPart): AuthBaseRequest {
    override var userId: String? = null

    override fun validate(): Boolean {
        return true
    }
}