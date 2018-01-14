package es.jarroyo.daggerandkotlin.domain.model

import java.io.Serializable


class DataHome(val id: Int? = null,
               val title: String? = null,
               val description: String? = null,
               val pageCount: Int? = null,
               val printPrice: Float? = null,
               val thumbnailUrl: String? = null,
               val imagesUrl: List<String>? = null,
               //var creators: List<Creator>? = null,
               var characters: List<Character>? = null) : Serializable {

    fun hasAllInfo(): Boolean {
        return (id != null && id > 0 && title != null && description != null && thumbnailUrl != null
                && imagesUrl != null && !imagesUrl.isEmpty())
    }
}