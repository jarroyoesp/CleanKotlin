package es.jarroyo.daggerandkotlin.domain.usecase.base

interface BaseResponse {

    fun onNetworkConnectionError()

    fun onNetworkServiceError()

    fun onUnknownError()

    fun onUserIsNotLoggedIn()
}
