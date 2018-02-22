package es.jarroyo.daggerandkotlin.data.source.network

import es.jarroyo.daggerandkotlin.data.entity.HomeEntity
import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.data.exception.IncorrectAuthenticationCredentialsException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.exception.UserAlreadyExistsException
import es.jarroyo.daggerandkotlin.data.mapper.NetworkAuthenticationResponseToUserEntityMapper
import es.jarroyo.daggerandkotlin.data.mapper.NetworkGetHomeResponseToHomeEntityMapper
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkError
import es.jarroyo.daggerandkotlin.data.source.network.request.body.NetworkBodyRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.body.getPain.NetworkGetPainRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.getHome.NetworkGetHomeRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.login.NetworkLoginRequest
import es.jarroyo.daggerandkotlin.data.source.network.request.signup.NetworkSignUpRequest
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeRequest
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpRequest


class NetworkDataSource(private var networkClientManager: NetworkClientManager,
                        private val networkAuthenticationResponseToUserEntityMapper: NetworkAuthenticationResponseToUserEntityMapper,
                        private val networkGetHomeResponseToHomeEntityMapper: NetworkGetHomeResponseToHomeEntityMapper) {

    fun getHome(request: GetHomeRequest): Response<List<HomeEntity>> {
        val networkResponse = NetworkGetHomeRequest(request, networkClientManager).run()

        if (!networkResponse.isSuccessful) {
            throw NetworkServiceException()
        }

        return Response(networkGetHomeResponseToHomeEntityMapper.map(networkResponse.data!!))
    }

    /***********************************************************************************************
     * LOGIN
     **********************************************************************************************/
    fun login(request: LoginRequest): Response<UserEntity> {
        val networkResponse = NetworkLoginRequest(request, networkClientManager).run()

        if (networkResponse.isSuccessful) {
            return Response(networkAuthenticationResponseToUserEntityMapper.map(networkResponse.data!!))
        } else {
            if (networkResponse.error?.error
                            .equals(NetworkError.Code.INCORRECT_AUTHENTICATION_CREDENTIALS.toString())) {
                throw IncorrectAuthenticationCredentialsException()
            }
            throw NetworkServiceException()
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

    /***********************************************************************************************
     * GET PAIN
     **********************************************************************************************/
    fun getPain(request: GetPainRequest): Response<List<BodyPart>> {

        val networkResponse = NetworkGetPainRequest(request, networkClientManager).run()
        if (networkResponse.isSuccessful) {
            return Response(networkResponse.data!!.bodyPartList)
        } else {
            throw NetworkServiceException()
        }
    }

    /***********************************************************************************************
     * SAVE PAIN
     **********************************************************************************************/
    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {

        val networkResponse = NetworkBodyRequest(request, networkClientManager).run()
        if (networkResponse.isSuccessful) {
            var bodyPartList = mutableListOf<BodyPart>()

            var bodyPart1 = BodyPart("1", "Knee123", 4)
            bodyPartList.add(bodyPart1)

            return Response(bodyPartList)
        } else {
            throw NetworkServiceException()
        }
    }
}
