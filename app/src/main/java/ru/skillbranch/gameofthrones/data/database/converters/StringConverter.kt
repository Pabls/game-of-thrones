package ru.skillbranch.gameofthrones.data.database.converters

import androidx.room.TypeConverter

class StringConverter {

    @TypeConverter
    fun fromString(value: String): List<String> = value.split(";")

    @TypeConverter
    fun fromArrayList(list: List<String>) = list.joinToString(";")
}