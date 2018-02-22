package es.jarroyo.daggerandkotlin.domain.usecase.body

import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class SavePainRequest(var bodypart: BodyPart): BaseRequest {

    override fun validate(): Boolean {
        return true
    }
}