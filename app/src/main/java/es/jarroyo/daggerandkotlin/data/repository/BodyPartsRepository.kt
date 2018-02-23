package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.entity.PainEntity
import es.jarroyo.daggerandkotlin.data.exception.UserNotFoundException
import es.jarroyo.daggerandkotlin.data.mapper.PainEntityDataMapper
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
                         private val cacheDataSource: CacheDataSource,
                         private val entityDataMapper: PainEntityDataMapper) {


    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {
        val response = networkDataSource.savePain(addUserIdToRequest(request) as SavePainRequest)
        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

    fun getPain(request: GetPainRequest): Response<List<BodyPart>> {
        val painListUser = diskDataSource.getPainList(diskDataSource.getUser()!!.id)
        if ( painListUser != null && painListUser.size > 0){
            return Response(entityDataMapper.mapList(painListUser))
        }

        addUserIdToRequest(request)
        val response = networkDataSource.getPain(addUserIdToRequest(request) as GetPainRequest)

        savePainList(response.data!!)

        val bodyPartList = response.data!!
        return Response(entityDataMapper.mapList(bodyPartList))
    }


    private fun savePainList(painList: List<PainEntity>) {
        painList?.let {
            diskDataSource.insertPain(it[0])
        }
    }

    /**
     * Add to REQUEST the ID of the current User
     * If in not loggedin throw an exception
     */
    private fun addUserIdToRequest(request: AuthBaseRequest): AuthBaseRequest{
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