package ru.skillbranch.gameofthrones.data.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface Api {

    @GET("houses")
    fun getHouses(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 50
    ): Call<List<HouseRes>>

    @GET("characters")
    fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 50
    ): Call<List<CharacterRes>>
}