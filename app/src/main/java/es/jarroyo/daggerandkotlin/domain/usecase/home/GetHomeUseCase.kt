package es.jarroyo.daggerandkotlin.domain.usecase.home

import es.jarroyo.daggerandkotlin.data.exception.MapperException
import es.jarroyo.daggerandkotlin.data.exception.NetworkConnectionException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.repository.HomeRepository
import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.executor.MainThread
import es.jarroyo.daggerandkotlin.domain.usecase.executor.UseCaseExecutor


class GetHomeUseCase(private val homeRepository: HomeRepository,
                     executor: UseCaseExecutor, mainThread: MainThread) :
        BaseUseCase<GetHomeRequest, GetHomeResponse>(executor, mainThread) {

    override fun run() {
        try {
            val response = homeRepository.getComics(request!!)
            notifyComicsReceived(response.data!!)
        } catch (e: NetworkConnectionException) {
            notifyNetworkConnectionError()
        } catch (e: NetworkServiceException) {
            notifyNetworkServiceError()
        } catch (e: MapperException) {
            notifyUnknownError()
        }

    }

    private fun notifyComicsReceived(homeList: List<DataHome>) {
        mainThread.post(Runnable { response!!.onDataHomeReceived(homeList) })
    }
}