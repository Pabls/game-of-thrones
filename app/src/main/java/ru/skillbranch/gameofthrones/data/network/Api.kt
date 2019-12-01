package ru.skillbranch.gameofthrones.data.network

import retrofit2.Call
import retrofit2.http.GET
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface Api {

    @GET("")
    suspend fun getHouses(): Call<List<HouseRes>>
}