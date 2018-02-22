package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.data.exception.UserNotFoundException
import es.jarroyo.daggerandkotlin.data.mapper.UserEntityDataMapper
import es.jarroyo.daggerandkotlin.data.source.cache.CacheDataSource
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpRequest


class UserRepository(private val networkDataSource: NetworkDataSource,
        /*private val diskDataSource: DiskDataSource,*/
        private val cacheDataSource: CacheDataSource,
                     private val userEntityDataMapper: UserEntityDataMapper) {

    fun getCurrentUser(): Response<User> {
        var user = cacheDataSource.user
        //if (user == null) user = diskDataSource.getUser()
        if (user == null) throw UserNotFoundException()
        return Response(userEntityDataMapper.map(user))
    }

    fun login(request: LoginRequest): Response<User> {
        val response = networkDataSource.login(request)
        var user = response.data!!
        saveUser(user)
        return Response(userEntityDataMapper.map(user))
    }

    fun signUp(request: SignUpRequest): Response<User> {
        val response = networkDataSource.signUp(request)
        val user = response.data!!
        saveUser(user)
        return Response(userEntityDataMapper.map(user))
    }

    private fun saveUser(user: UserEntity?) {
        user?.let {
            cacheDataSource.user = user
            /* diskDataSource.insertUser(it)*/
        }
    }

/*fun recoverPassword(request: RecoverPasswordRequest): Response<Void>
        = networkDataSource.recoverPassword(request)*/

    fun signOut() {
        /*diskDataSource.deleteAllTables()*/
    }
}