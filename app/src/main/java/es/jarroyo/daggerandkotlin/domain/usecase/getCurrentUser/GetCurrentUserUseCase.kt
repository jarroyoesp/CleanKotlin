package es.jarroyo.daggerandkotlin.domain.usecase.getCurrentUser

import es.jarroyo.daggerandkotlin.data.exception.UserNotFoundException
import es.jarroyo.daggerandkotlin.data.repository.UserRepository
import es.jarroyo.daggerandkotlin.domain.model.User
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor

class GetCurrentUserUseCase(private val userRepository: UserRepository,
                            executor: UseCaseExecutor, mainThread: MainThread) :
        BaseUseCase<GetCurrentUserRequest, GetCurrentUserResponse>(executor, mainThread) {

    override fun run() {
        try {
            val response = userRepository.getCurrentUser()
            notifyCurrentUserReceived(response.data!!)
        } catch (e: UserNotFoundException) {
            notifyUserNotFound()
        }
    }

    private fun notifyCurrentUserReceived(user: User) {
        mainThread.post(Runnable { response!!.onCurrentUserReceived(user) })
    }

    private fun notifyUserNotFound() {
        mainThread.post(Runnable { response!!.onUserIsNotLoggedIn() })
    }
}