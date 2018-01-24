package es.jarroyo.daggerandkotlin.domain.usecase.signUp

import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseRequest
import es.jarroyo.daggerandkotlin.domain.validator.UserValidator

/**
 * Created by javierarroyo on 24/1/18.
 */
class SignUpRequest(var name: String, var surname: String,
                    var email: String, var password: String,
                    var repeatedPassword: String,
                    private val validator: Validator) : BaseRequest {
    override fun validate(): Boolean {
        if (!(UserValidator isNameValid (name))) {
            validator.onIncorrectNameFormat()
            return false
        }

        if (!(UserValidator isSurnameValid (surname))) {
            validator.onIncorrectSurnameFormat()
            return false
        }

        if (!(UserValidator isEmailFormatValid (email))) {
            validator.onIncorrectEmailFormat()
            return false
        }

        if (!(UserValidator isPasswordValid (password))) {
            validator.onIncorrectPasswordFormat()
            return false
        }

        if (!UserValidator.arePasswordsEquals(password, repeatedPassword)) {
            validator.onIncorrectMatchingPasswords()
            return false
        }

        return true
    }

    interface Validator {
        fun onIncorrectNameFormat()

        fun onIncorrectSurnameFormat()

        fun onIncorrectEmailFormat()

        fun onIncorrectPasswordFormat()

        fun onIncorrectMatchingPasswords()
    }
}