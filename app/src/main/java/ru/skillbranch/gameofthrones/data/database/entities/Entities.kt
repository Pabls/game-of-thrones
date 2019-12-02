package ru.skillbranch.gameofthrones.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.skillbranch.gameofthrones.data.database.converters.StringConverter

@Entity(tableName = "characters")
data class CharacterDto(
    @PrimaryKey
    val id: String,
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val house: String,
    @TypeConverters(StringConverter::class)
    val titles: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val aliases: List<String> = listOf(),
    val father: String,
    val mother: String,
    val spouse: String,
    @TypeConverters(StringConverter::class)
    val allegiances: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val books: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val povBooks: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val tvSeries: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val playedBy: List<String> = listOf(),
    val words: String
)

@Entity(tableName = "houses")
data class HouseDto(
    @PrimaryKey
    val shortName: String,
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    @TypeConverters(StringConverter::class)
    val titles: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val seats: List<String> = listOf(),
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    @TypeConverters(StringConverter::class)
    val ancestralWeapons: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val cadetBranches: List<String> = listOf(),
    @TypeConverters(StringConverter::class)
    val swornMembers: List<String> = listOf()
)