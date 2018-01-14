package es.jarroyo.daggerandkotlin.data.source.network.request.base


import es.jarroyo.daggerandkotlin.data.exception.NetworkConnectionException
import es.jarroyo.daggerandkotlin.data.exception.NetworkServiceException
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import retrofit2.Call

abstract class NetworkBaseRequest<T> {

    var call: Call<T>? = null
        private set

    @Throws(NetworkConnectionException::class, NetworkServiceException::class)
    protected fun execute(call: Call<T>): NetworkResponse<T> {
        this.call = call
        return NetworkManager.execute(this)
    }

    @Throws(NetworkConnectionException::class, NetworkServiceException::class)
    abstract fun run(): NetworkResponse<T>
}
