package ru.skillbranch.gameofthrones.data.database.converters

import androidx.room.TypeConverter

class StringConverter {

    private val DELIMITER = ";"

    @TypeConverter
    fun fromString(value: String): List<String> = value.split(DELIMITER)

    @TypeConverter
    fun fromArrayList(list: List<String>) = list.joinToString(DELIMITER)
}