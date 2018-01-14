package es.jarroyo.daggerandkotlin.data.repository

import es.jarroyo.daggerandkotlin.data.mapper.HomeEntityDataMapper
import es.jarroyo.daggerandkotlin.data.source.network.NetworkDataSource
import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.base.Response
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeRequest

class HomeRepository(private val networkDataSource: NetworkDataSource,
                     private val comicEntityDataMapper: HomeEntityDataMapper) {

    fun getComics(request: GetHomeRequest): Response<List<DataHome>> {
        val response = networkDataSource.getHome(request)
        val comicEntities = response.data!!
        val comics = comicEntities.map { comicEntityDataMapper.map(it) }
        return Response(comics)
    }

}