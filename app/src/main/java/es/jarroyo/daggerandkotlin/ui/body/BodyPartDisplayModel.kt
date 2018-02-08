package es.jarroyo.daggerandkotlin.ui.body

import java.io.Serializable

/**
 * Created by javierarroyo on 8/2/18.
 */
class BodyPartDisplayModel (var id: Int,
                            var nameBodyPart: String?,
                            var painLevel: Int) : Serializable {
}