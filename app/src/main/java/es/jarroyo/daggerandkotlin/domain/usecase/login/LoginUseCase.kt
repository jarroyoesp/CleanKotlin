package es.jarroyo.daggerandkotlin.domain.usecase.login

import es.jarroyo.daggerandkotlin.data.exception.IncorrectAuthenticationCredentialsException
import es.jarroyo.daggerandkotlin.data.exception.MapperException
import es.jarroyo.daggerandkotlin.data.exception.NetworkConnectionException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor
import java.security.NoSuchAlgorithmException


class LoginUseCase(private val userRepository: UserRepository,
                   executor: UseCaseExecutor, mainThread: MainThread) :
        BaseUseCase<LoginRequest, LoginResponse>(executor, mainThread) {

    override fun run() {
        try {
            request!!.securePassword()
            val response = userRepository.login(request!!)
            notifyUserLoggedIn(response.data!!)
        } catch (e: NetworkConnectionException) {
            notifyNetworkConnectionError()
        } catch (e: NetworkServiceException) {
            notifyNetworkServiceError()
        } catch (e: IncorrectAuthenticationCredentialsException) {
            notifyIncorrectLoginUserData()
        } catch (e: NoSuchAlgorithmException) {
            notifyUnknownError()
        } catch (e: MapperException) {
            notifyUnknownError()
        }


    }

    private fun notifyUserLoggedIn(user: User) {
        mainThread.post(Runnable { response!!.onUserLoggedIn(user) })
    }

    private fun notifyIncorrectLoginUserData() {
        mainThread.post(Runnable { response!!.onIncorrectLoginUserData() })
    }
}
