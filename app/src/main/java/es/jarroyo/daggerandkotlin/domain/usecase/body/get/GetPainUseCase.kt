package es.jarroyo.daggerandkotlin.domain.usecase.body.get

import es.jarroyo.daggerandkotlin.data.repository.BodyPartRepository
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor


class GetPainUseCase (private val bodyPartRepository: BodyPartRepository,
                      executor: UseCaseExecutor,
                      mainThread: MainThread) :
        BaseUseCase<GetPainRequest, GetPainResponse>(executor, mainThread) {

    override fun run() {
        try {
            val response = bodyPartRepository.getPain(request!!)
            notifySavePainReceived(response.data!!)
        } catch (e: Exception) {
            notifyUnknownError()
        }

    }

    private fun notifySavePainReceived(bodyPartList: List<BodyPart>) {
        mainThread.post(Runnable { response!!.onPainListReceived(bodyPartList) })
    }
}