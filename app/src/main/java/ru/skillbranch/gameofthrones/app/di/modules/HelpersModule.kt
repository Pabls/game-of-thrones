package ru.skillbranch.gameofthrones.app.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class HelpersModule {

    companion object {
        private val gson = GsonBuilder().create()
    }

    fun getGson(): Gson = gson
}