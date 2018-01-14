package es.jarroyo.daggerandkotlin.ui.home

import es.jarroyo.daggerandkotlin.domain.model.DataHome
import java.util.*


class HomeDisplayModel(comics: List<DataHome>) {

    private val comicDisplayModels = ArrayList<ComicDisplayModel>()

    init {
        comics.mapTo(comicDisplayModels) { ComicDisplayModel(it) }
    }

    fun get(position: Int): ComicDisplayModel = comicDisplayModels[position]

    fun size(): Int = comicDisplayModels.size

    inner class ComicDisplayModel(comic: DataHome) {
        val title: String = comic.title!!
        val thumbnail: String = comic.thumbnailUrl!!
    }
}
