package ru.skillbranch.gameofthrones.data.database.dao

import androidx.room.*
import ru.skillbranch.gameofthrones.data.database.converters.StringConverter
import ru.skillbranch.gameofthrones.data.database.entities.CharacterDto

@Dao
@TypeConverters(StringConverter::class)
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterDto>)

    @Query(
        """
        SELECT * FROM characters;
    """
    )
    suspend fun getCharacters(): List<CharacterDto>

    @Query(
        """
        SELECT * FROM characters WHERE house=:house;
    """
    )
    suspend fun getCharactersByHouseName(house: String): List<CharacterDto>

    @Query(
        """
        SELECT * FROM characters WHERE url=:id;
    """
    )
    suspend fun getCharactersById(id: String): CharacterDto
}