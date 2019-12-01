package ru.skillbranch.gameofthrones.app.di.modules

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.data.network.Api

class NetworkModule(private val gson: Gson) {

    init {
        client = OkHttpClient.Builder().build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(AppConfig.BASE_URL)
            .build()

        api = retrofit.create(Api::class.java)
    }

    companion object {
        lateinit var client: OkHttpClient
        lateinit var retrofit: Retrofit
        lateinit var api: Api
    }

    fun getApi(): Api = api
}