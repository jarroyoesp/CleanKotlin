package es.jarroyo.daggerandkotlin.domain.usecase.base

interface AuthBaseRequest: BaseRequest {
    var userId: String?
}
