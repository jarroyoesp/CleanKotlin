package es.jarroyo.daggerandkotlin.domain.usecase.login

import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse


interface LoginResponse : BaseResponse {

    fun onUserLoggedIn(user: User)

    fun onIncorrectLoginUserData()
}
