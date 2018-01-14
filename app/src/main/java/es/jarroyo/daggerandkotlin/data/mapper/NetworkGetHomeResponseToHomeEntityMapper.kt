package es.jarroyo.daggerandkotlin.data.mapper

import android.net.Network
import es.jarroyo.daggerandkotlin.data.entity.HomeEntity
import es.jarroyo.daggerandkotlin.data.source.network.request.getHome.NetworkGetHomeResponse
import es.jarroyo.daggerandkotlin.data.source.network.request.getHome.NetworkResult



class NetworkGetHomeResponseToHomeEntityMapper : Mapper<NetworkGetHomeResponse, List<HomeEntity>> {

    override fun map(input: NetworkGetHomeResponse): List<HomeEntity> =
            input.data.results.map {
                HomeEntity(
                        getId(it),
                        getTitle(it),
                        getDescription(it),
                        getPageCount(it),
                        getPrintPrice(it),
                        getThumbnailUrl(it),
                        getImagesUrl(it)/*,
                        getCreators(it),
                        getCharacters(it)*/
                )

            }

    private fun getId(input: NetworkResult): Int? = input.id

    private fun getTitle(input: NetworkResult): String? = input.title

    private fun getDescription(input: NetworkResult): String? = input.description

    private fun getPageCount(input: NetworkResult): Int? = input.pageCount

    private fun getPrintPrice(input: NetworkResult): Float? =
            input.prices?.first { it.type == "printPrice" }?.price

    private fun getThumbnailUrl(input: NetworkResult): String? =
            input.thumbnail?.path + "." + input.thumbnail?.extension

    private fun getImagesUrl(input: NetworkResult): List<String>? =
            input.images?.map { it.path + "." + it.extension }

/*    private fun getCreators(input: NetworkResult): List<CreatorEntity>? =
            input.creators?.creators?.map {
                CreatorEntity(
                        it.name,
                        it.role
                )
            }

    private fun getCharacters(input: NetworkResult): List<CharacterEntity>? =
            input.characters?.characters?.map {
                CharacterEntity(
                        it.name
                )
            }*/
}