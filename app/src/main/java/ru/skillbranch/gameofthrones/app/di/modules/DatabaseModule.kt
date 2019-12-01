package ru.skillbranch.gameofthrones.app.di.modules

import android.content.Context
import androidx.room.Room
import ru.skillbranch.gameofthrones.data.database.AppDatabase

class DatabaseModule(private val context: Context) {

    init {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,  AppDatabase.DATABASE_NAME
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
    }

    fun getDataBase(): AppDatabase = db
}