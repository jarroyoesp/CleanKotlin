package es.jarroyo.daggerandkotlin.data.source.network.request.getHome

import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.request.base.NetworkBaseRequest
import es.jarroyo.daggerandkotlin.data.source.network.service.MarvelAPI

abstract class NetworkMarvelRequest<T>(networkClientManager: NetworkClientManager) : NetworkBaseRequest<T>() {
    open var API: MarvelAPI = networkClientManager.marvelAuthClient
}