package es.jarroyo.daggerandkotlin.domain.usecase.body.save

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class SavePainRequest(var bodypart: BodyPart): BaseRequest {

    override fun validate(): Boolean {
        return true
    }
}