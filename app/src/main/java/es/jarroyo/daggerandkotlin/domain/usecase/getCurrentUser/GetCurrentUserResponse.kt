package es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser

import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse


interface GetCurrentUserResponse: BaseResponse {

    fun onCurrentUserReceived(user: User)

}