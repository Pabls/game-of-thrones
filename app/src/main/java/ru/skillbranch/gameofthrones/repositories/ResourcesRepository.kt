package ru.skillbranch.gameofthrones.repositories

import android.content.Context

object ResourcesRepository : IResourcesRepository {
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    override fun getStringById(id: Int): String = context.getString(id)
}