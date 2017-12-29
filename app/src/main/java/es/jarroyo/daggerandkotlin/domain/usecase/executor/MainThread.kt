package es.jarroyo.daggerandkotlin.domain.usecase.executor

interface MainThread {
    fun post(runnable: Runnable)
}
