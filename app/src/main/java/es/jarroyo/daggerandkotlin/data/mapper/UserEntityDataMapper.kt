package es.jarroyo.daggerandkotlin.data.mapper

import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.domain.model.User


class UserEntityDataMapper : Mapper<UserEntity, User> {

    override fun map(input: UserEntity): User =
            User(
                    getId(input),
                    getName(input),
                    getSurname(input),
                    getPhoto(input),
                    getEmail(input),
                    getPassword(input))

    private fun getId(input: UserEntity): String{
       return input.id
    }

    private fun getName(input: UserEntity) = input.name

    private fun getSurname(input: UserEntity) = input.surname

    private fun getPhoto(input: UserEntity) = input.photo

    private fun getEmail(input: UserEntity) = input.email

    private fun getPassword(input: UserEntity) = input.password
}