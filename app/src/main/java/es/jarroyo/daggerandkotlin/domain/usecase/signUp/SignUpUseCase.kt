package es.jarroyo.daggerandkotlin.domain.usecase.signUp

import es.jarroyo.daggerandkotlin.data.exception.MapperException
import es.jarroyo.daggerandkotlin.data.exception.NetworkConnectionException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.exception.UserAlreadyExistsException
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor

/**
 * Created by javierarroyo on 24/1/18.
 */
class SignUpUseCase(private val userRepository: UserRepository,
                    executor: UseCaseExecutor, mainThread: MainThread):
        BaseUseCase<SignUpRequest, SignUpResponse>(executor, mainThread){

    override fun run() {
        try {
            //request!!.securePassword()
            val response = userRepository.signUp(request!!)
            notifyUserSignedIn(response.data!!)
        } catch (e: NetworkConnectionException) {
            notifyNetworkConnectionError()
        } catch (e: NetworkServiceException) {
            notifyNetworkServiceError()
        } catch (e: UserAlreadyExistsException) {
            notifyUserAlreadyExists()
        } catch (e: java.security.NoSuchAlgorithmException) {
            notifyUnknownError()
        } catch (e: MapperException) {
            notifyUnknownError()
        }

    }

    private fun notifyUserSignedIn(user: User) {
        mainThread.post(Runnable { response!!.onUserSignedIn(user) })
    }

    private fun notifyUserAlreadyExists() {
        mainThread.post(Runnable { response!!.onUserAlreadyExists() })
    }
}