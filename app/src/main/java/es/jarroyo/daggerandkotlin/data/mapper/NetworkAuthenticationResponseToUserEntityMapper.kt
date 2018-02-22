package es.jarroyo.daggerandkotlin.data.mapper

import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.data.exception.MapperException
import es.jarroyo.daggerandkotlin.data.source.network.request.base.auth.NetworkUserAuthenticationResponse


class NetworkAuthenticationResponseToUserEntityMapper : Mapper<NetworkUserAuthenticationResponse, UserEntity> {

    @Throws(MapperException::class)
    override fun map(input: NetworkUserAuthenticationResponse): UserEntity {
        val user = UserEntity(
                id = getId(input),
                name = getName(input),
                surname = getSurname(input),
                photo = getPhoto(input),
                email = getEmail(input))
        return user
    }

    private fun getId(input: NetworkUserAuthenticationResponse): String
            = input.id

    private fun getName(input: NetworkUserAuthenticationResponse): String
            = input.name

    private fun getSurname(input: NetworkUserAuthenticationResponse): String
            = input.surname

    private fun getPhoto(input: NetworkUserAuthenticationResponse): String
            = input.photo

    private fun getEmail(input: NetworkUserAuthenticationResponse): String
            = input.email
}
