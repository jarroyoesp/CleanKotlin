package es.jarroyo.daggerandkotlin.domain.validator

import es.jarroyo.daggerandkotlin.BuildConfig
import es.jarroyo.daggerandkotlin.app.util.Util

object UserValidator {

    infix fun isNameValid(name: String?): Boolean {
        if (name == null ||
                name.isEmpty() ||
                name.length < BuildConfig.MIN_NAME_LENGTH) {
            return false
        }

        return true
    }

    infix fun isSurnameValid(surname: String?): Boolean {
        if (surname == null ||
                surname.isEmpty() ||
                surname.length < BuildConfig.MIN_SURNAME_LENGTH) {
            return false
        }
        return true
    }

    infix fun isEmailFormatValid(email: String?): Boolean {
        if (email == null || !Util.isValidEmailFormat(email)) {
            return false
        }
        return true
    }

    infix fun isPasswordValid(password: String?): Boolean {
        val passwordPattern = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=\\S+$)" +
                ".{8,}$"

        if (password == null || !password.matches(passwordPattern.toRegex())) {
            return false
        }
        return true
    }


    fun arePasswordsEquals(password: String, repeatPassword: String?): Boolean {
        if (repeatPassword == null || repeatPassword != password) {
            return false
        }
        return true
    }

    infix fun isPhoneLengthValid(phone: String?): Boolean {
        if (phone == null || phone.length != BuildConfig.PHONE_LENGTH) {
            return false
        }
        return true
    }
}