package ru.skillbranch.gameofthrones.app.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MappersModule {

    companion object {
        private val gson = GsonBuilder().create()
    }

    fun provideGson(): Gson = gson
}