package es.jarroyo.daggerandkotlin.data.source.network.request.body

import com.google.gson.annotations.SerializedName

/**
 * Created by javierarroyo on 21/2/18.
 */
class NetworkBodyResponse (@SerializedName("bodyPart") val data: String){

}