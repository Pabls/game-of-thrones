package ru.skillbranch.gameofthrones.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.skillbranch.gameofthrones.BuildConfig
import ru.skillbranch.gameofthrones.data.database.converters.StringConverter
import ru.skillbranch.gameofthrones.data.database.dao.CharactersDao
import ru.skillbranch.gameofthrones.data.database.dao.HousesDao
import ru.skillbranch.gameofthrones.data.database.entities.CharacterDto
import ru.skillbranch.gameofthrones.data.database.entities.HouseDto

@Database(entities = arrayOf(
    CharacterDto::class,
    HouseDto::class), version = AppDatabase.DATABASE_VERSION)
@TypeConverters(StringConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = BuildConfig.APPLICATION_ID + ".database"
        const val DATABASE_VERSION = 1
    }

    abstract fun getCharactersDao(): CharactersDao
    abstract fun getHouseDao(): HousesDao
}