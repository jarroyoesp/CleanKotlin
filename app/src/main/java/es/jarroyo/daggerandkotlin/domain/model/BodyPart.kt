package es.jarroyo.daggerandkotlin.domain.model

/**
 * Created by javierarroyo on 20/2/18.
 */
data class BodyPart(var id: String = "",
               var name: String? = null,
               var painLevel: Int = 0,
               var userId: String? = null) {
}