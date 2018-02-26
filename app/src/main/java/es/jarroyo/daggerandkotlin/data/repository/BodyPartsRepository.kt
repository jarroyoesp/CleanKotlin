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
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest


class BodyPartRepository(private val networkDataSource: NetworkDataSource,
                         private val diskDataSource: DiskDataSource,
                         private val cacheDataSource: CacheDataSource,
                         private val entityDataMapper: PainEntityDataMapper) {




    fun savePain(request: SavePainRequest): Response<List<BodyPart>> {
        val userId = getCurrentUserId()
        request.bodypart.userId = userId
        diskDataSource.insertPain(entityDataMapper.mapToEntity(request.bodypart))
        val response = networkDataSource.savePain(addUserIdToRequest(userId, request) as SavePainRequest)

        val bodyPartList = response.data!!
        return Response(bodyPartList)
    }

    fun getPain(request: GetPainRequest, callback: GetPainUseCase.GetPainCallback){
        // Obtenemos de DataBase
        val painListUser = diskDataSource.getPainList(diskDataSource.getUser()!!.id)
        if ( painListUser != null && painListUser.size > 0){
            callback.onSuccess( Response(entityDataMapper.mapList(painListUser)))
        }
        val userId = getCurrentUserId()
        val response = networkDataSource.getPain(addUserIdToRequest(userId, request) as GetPainRequest)

        savePainList(response.data!!)

        val bodyPartList = response.data!!
        callback.onSuccess( Response(entityDataMapper.mapList(bodyPartList)))
    }


    private fun savePainList(painList: List<PainEntity>) {
        painList?.let {
            diskDataSource.insertPainList(it)
        }
    }

    private fun getCurrentUserId(): String{
        // Asignamos el id del usuario logueado
        if (cacheDataSource.user != null) {
            return cacheDataSource.user!!.id
        } else if (diskDataSource.getUser() != null) {
            return diskDataSource.getUser()!!.id
        } else {
            throw UserNotFoundException()
        }
    }

    /**
     * Add to REQUEST the ID of the current User
     * If in not loggedin throw an exception
     */
    private fun addUserIdToRequest(userId: String, request: AuthBaseRequest): AuthBaseRequest{
        // Asignamos el id del usuario logueado
        request.userId = userId

        return request
    }

}