package es.jarroyo.daggerandkotlin.domain.usecase.base

interface BaseRequest {

    fun validate(): Boolean
}
