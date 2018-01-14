package es.jarroyo.daggerandkotlin.data.source.network.service

import es.jarroyo.daggerandkotlin.data.source.network.request.getHome.NetworkGetHomeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface MarvelAPI {

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(@Path("characterId") characterId: Int,
            @QueryMap params: HashMap<String, Any>): Call<NetworkGetHomeResponse>
}