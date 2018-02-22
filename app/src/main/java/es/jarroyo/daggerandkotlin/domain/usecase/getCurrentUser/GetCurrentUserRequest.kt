package es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser

import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class GetCurrentUserRequest: BaseRequest {

    override fun validate(): Boolean {
        return true
    }
}