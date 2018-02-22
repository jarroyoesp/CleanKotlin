package es.jarroyo.daggerandkotlin.domain.usecase.body.save

import es.jarroyo.daggerandkotlin.data.repository.BodyPartRepository
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainResponse
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor

/**
 * Created by javierarroyo on 20/2/18.
 */
class SavePainUseCase(private val bodyPartRepository: BodyPartRepository, executor: UseCaseExecutor, mainThread: MainThread) :
        BaseUseCase<SavePainRequest, SavePainResponse>(executor, mainThread) {
    override fun run() {
        try {
            val response = bodyPartRepository.savePain(request!!)
            notifySavePainReceived(response.data!!)
        } catch (e: Exception) {
            notifyUnknownError()
        }

    }

    private fun notifySavePainReceived(bodyPartList: List<BodyPart>) {
        mainThread.post(Runnable { response!!.onSavePainReceived(bodyPartList) })
    }
}