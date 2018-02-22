package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.source.cache.CacheDataSource
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.body.SavePainRequest


class BodyPartRepository(private val networkDataSource: NetworkDataSource,
                         private val cacheDataSource: CacheDataSource) {


    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {
        // Asignamos el id del usuario logueado
        if (cacheDataSource.user != null) {
            request.bodypart.userId = cacheDataSource.user!!.id
        }


        val response = networkDataSource.savePain(request)
        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

}