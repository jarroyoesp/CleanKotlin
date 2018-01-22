package es.jarroyo.daggerandkotlin.data.source.network.request.signup

import com.google.gson.annotations.SerializedName


class NetworkSignUpResponse(@SerializedName("data") val data: NetworkData)

class NetworkData(@SerializedName("user") val user: String)
