package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.exception.UserNotFoundException
import es.jarroyo.daggerandkotlin.data.source.cache.CacheDataSource
import es.jarroyo.daggerandkotlin.data.source.disk.DiskDataSource
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.AuthBaseRequest
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest


class BodyPartRepository(private val networkDataSource: NetworkDataSource,
                         private val diskDataSource: DiskDataSource,
                         private val cacheDataSource: CacheDataSource) {


    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {
        // Asignamos el id del usuario logueado
        /*if (cacheDataSource.user != null) {
            request.userId = cacheDataSource.user!!.id
        } else if (diskDataSource.getUser() != null) {
            request.userId = diskDataSource.getUser()!!.id
        } else {
            throw UserNotFoundException()
        }*/

        val response = networkDataSource.savePain(addUserIdToRequest(request) as SavePainRequest)
        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

    fun getPain(request: GetPainRequest): Response<List<BodyPart>> {

        // Asignamos el id del usuario logueado
        /*if (cacheDataSource.user != null) {
            request.userId = cacheDataSource.user!!.id
        } else if (diskDataSource.getUser() != null) {
            request.userId = diskDataSource.getUser()!!.id
        } else {
            throw UserNotFoundException()
        }*/

        addUserIdToRequest(request)
        val response = networkDataSource.getPain(addUserIdToRequest(request) as GetPainRequest)
        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

    fun addUserIdToRequest(request: AuthBaseRequest): AuthBaseRequest{
        // Asignamos el id del usuario logueado
        if (cacheDataSource.user != null) {
            request.userId = cacheDataSource.user!!.id
        } else if (diskDataSource.getUser() != null) {
            request.userId = diskDataSource.getUser()!!.id
        } else {
            throw UserNotFoundException()
        }
        return request
    }

}