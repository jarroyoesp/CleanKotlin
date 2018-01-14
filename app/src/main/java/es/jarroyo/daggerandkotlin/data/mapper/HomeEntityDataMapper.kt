package es.jarroyo.daggerandkotlin.data.mapper

import es.jarroyo.daggerandkotlin.data.entity.HomeEntity
import es.jarroyo.daggerandkotlin.domain.model.DataHome


class HomeEntityDataMapper : Mapper<HomeEntity, DataHome> {

    override fun map(input: HomeEntity): DataHome =
            DataHome(
                    getId(input),
                    getTitle(input),
                    getDescription(input),
                    getPageCount(input),
                    getPrintPrice(input),
                    getThumbnailUrl(input),
                    getImagesUrl(input)/*,
                    getCreators(input),
                    getCharacters(input)*/
            )

    private fun getId(input: HomeEntity): Int? = input.id

    private fun getTitle(input: HomeEntity): String? = input.title

    private fun getDescription(input: HomeEntity): String? = input.description

    private fun getPageCount(input: HomeEntity): Int? = input.pageCount

    private fun getPrintPrice(input: HomeEntity): Float? =
            input.printPrice

    private fun getThumbnailUrl(input: HomeEntity): String? =
            input.thumbnailUrl

    private fun getImagesUrl(input: HomeEntity): List<String>? =
            input.imagesUrl

   /* private fun getCreators(input: ComicEntity): List<Creator>? =
            input.creators?.map { Creator(it.name, it.role) }

    private fun getCharacters(input: ComicEntity): List<com.github.juan1393.cleanArchitectureKotlin.domain.model.Character>? =
            input.characters?.map { Character(it.name) }
*/}