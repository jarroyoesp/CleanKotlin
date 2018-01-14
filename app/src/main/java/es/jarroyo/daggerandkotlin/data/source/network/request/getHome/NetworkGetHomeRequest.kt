package es.jarroyo.daggerandkotlin.data.source.network.request.getHome

import es.jarroyo.daggerandkotlin.data.exception.NetworkConnectionException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeRequest

class NetworkGetHomeRequest(private val getComicsRequest: GetHomeRequest,
                            networkClientManager: NetworkClientManager)
    : NetworkMarvelRequest<NetworkGetHomeResponse>(networkClientManager) {

    companion object {
        private val FORMAT = "comic"
        private val OFFSET = "20" // Because latest comics has data issues
    }

    @Throws(NetworkConnectionException::class, NetworkServiceException::class)
    override fun run(): NetworkResponse<NetworkGetHomeResponse> {
        val params = hashMapOf(
                "format" to FORMAT,
                "formatType" to FORMAT,
                "offset" to OFFSET,
                "limit" to getComicsRequest.maxItems)

        val call = API.getComics(getComicsRequest.characterId, params)
        return execute(call)
    }
}