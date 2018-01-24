package es.jarroyo.daggerandkotlin.domain.usecase.signUp

import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseResponse

/**
 * Created by javierarroyo on 24/1/18.
 */
interface SignUpResponse : BaseResponse {

    fun onUserSignedIn(user: User)

    fun onUserAlreadyExists()
}
