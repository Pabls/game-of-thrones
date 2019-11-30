package ru.skillbranch.gameofthrones.repositories

import android.content.Context

object ResourcesRepository : IResourcesRepository {

    var context: Context
        get() {
            return context
        }
        set(value) {
            this.context = value
        }

    override fun getStringById(id: Int): String = context.getString(id)
}