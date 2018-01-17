package es.jarroyo.daggerandkotlin.ui.home

import es.jarroyo.daggerandkotlin.domain.model.DataHome
import java.util.*


class HomeDisplayModel(comics: List<DataHome>) {

    private val comicDisplayModels = ArrayList<DataHomeDisplayModel>()

    init {
        comics.mapTo(comicDisplayModels) { DataHomeDisplayModel(it) }
    }

    fun get(position: Int): DataHomeDisplayModel = comicDisplayModels[position]

    fun size(): Int = comicDisplayModels.size

    inner class DataHomeDisplayModel(comic: DataHome) {
        val title: String = comic.title!!
        val thumbnail: String = comic.thumbnailUrl!!
    }
}
