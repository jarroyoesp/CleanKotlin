package es.jarroyo.daggerandkotlin.data.source.network.manager

import es.jarroyo.daggerandkotlin.data.source.network.service.MarvelAPI


class NetworkClientManager(/*val diskDataSource: DiskDataSource*/) {

    /*val client: API = NetworkServiceManager.createService(API::class.java)

    val authClient: API
        get() {
            return NetworkServiceManager.createAuthService(
                    API::class.java,
                    diskDataSource.getToken()?.token ?: throw DataNotFoundException())
        }*/

    val marvelAuthClient: MarvelAPI
        get() {
            return NetworkServiceManager.createMarvelAuthService(
                    MarvelAPI::class.java)
        }

    /*fun updateToken(token: String) {
        diskDataSource.deleteAllTokens()
        diskDataSource.insertToken(TokenEntity(token))
    }*/
}
