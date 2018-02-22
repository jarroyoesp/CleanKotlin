package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.body.SavePainRequest


class BodyPartRepository(private val networkDataSource: NetworkDataSource
        /*private val diskDataSource: DiskDataSource,
        private val cacheDataSource: CacheDataSource,
                          private val userEntityDataMapper: UserEntityDataMapper*/) {


    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {
        val response = networkDataSource.savePain(request)
        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

}