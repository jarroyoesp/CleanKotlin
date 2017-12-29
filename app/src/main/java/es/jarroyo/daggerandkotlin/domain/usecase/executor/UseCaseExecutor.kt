package es.jarroyo.daggerandkotlin.domain.usecase.executor

import es.jarroyo.daggerandkotlin.domain.usecase.base.BaseUseCase


interface UseCaseExecutor {
    fun run(baseUseCase: BaseUseCase<*, *>)
}