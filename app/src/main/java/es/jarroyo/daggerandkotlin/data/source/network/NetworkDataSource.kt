package es.jarroyo.daggerandkotlin.data.source.network

import es.jarroyo.daggerandkotlin.data.entity.HomeEntity
import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.data.exception.IncorrectAuthenticationCredentialsException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.exception.UserAlreadyExistsException
import es.jarroyo.daggerandkotlin.data.mapper.NetworkGetHomeResponseToHomeEntityMapper
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkError
import es.jarroyo.daggerandkotlin.data.source.network.request.getHome.NetworkGetHomeRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.login.NetworkLoginRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.signup.NetworkSignUpRequest
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeRequest
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpRequest


class NetworkDataSource(private var networkClientManager: NetworkClientManager,
                        /*private val networkAuthenticationResponseToUserEntityMapper: NetworkAuthenticationResponseToUserEntityMapper,*/
                        private val networkGetHomeResponseToHomeEntityMapper: NetworkGetHomeResponseToHomeEntityMapper) {

    fun getHome(request: GetHomeRequest): Response<List<HomeEntity>> {
        val networkResponse = NetworkGetHomeRequest(request, networkClientManager).run()

        if (!networkResponse.isSuccessful) {
            throw NetworkServiceException()
        }

        return Response(networkGetHomeResponseToHomeEntityMapper.map(networkResponse.data!!))
    }

    fun login(request: LoginRequest): Response<UserEntity> {
        val networkResponse = NetworkLoginRequest( request, networkClientManager).run()

        if (!networkResponse.isSuccessful) {
            if (networkResponse.error?.error
                    .equals(NetworkError.Code.INCORRECT_AUTHENTICATION_CREDENTIALS.toString())) {
                throw IncorrectAuthenticationCredentialsException()
            }
            throw NetworkServiceException()
        } else {
            return Response(UserEntity("1", "Name", "Surname", "photo", "email@arroyo.com"))
        }
    }

    fun signUp(request: SignUpRequest): Response<UserEntity> {
        val networkResponse = NetworkSignUpRequest(request, networkClientManager).run()

        if (!networkResponse.isSuccessful) {
            if (networkResponse.error?.error
                            .equals(NetworkError.Code.USER_ALREADY_EXISTS.toString())) {
                throw UserAlreadyExistsException()
            }
            throw NetworkServiceException()
        } else {
            return Response(UserEntity("1", "Name", "Surname", "photo", "email@arroyo.com"))
        }
        //return Response(networkAuthenticationResponseToUserEntityMapper.map(networkResponse.data!!))
    }
}
