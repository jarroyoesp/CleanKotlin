package es.jarroyo.daggerandkotlin.domain.usecase.login

import es.jarroyo.daggerandkotlin.app.util.HASH
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest


class LoginRequest(var email: String, var password: String, private val validator: Validator)
    : BaseRequest {

    fun securePassword() {
        password = HASH.md5(password)
    }

    override fun validate(): Boolean {
        /*if (!(UserValidator isEmailFormatValid(email))) {
            validator.onIncorrectEmailFormat()
            return false
        }

        if (!(UserValidator isPasswordValid(password))) {
            validator.onIncorrectPasswordFormat()
            return false
        }*/

        return true
    }

    interface Validator {
        fun onIncorrectEmailFormat()

        fun onIncorrectPasswordFormat()
    }
}
