package ru.skillbranch.gameofthrones.data.database.dao

import androidx.room.*
import ru.skillbranch.gameofthrones.data.database.converters.StringConverter
import ru.skillbranch.gameofthrones.data.database.entities.HouseDto

@Dao
@TypeConverters(StringConverter::class)
interface HousesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouses(houses: List<HouseDto>)

    @Query(
        """
        SELECT * FROM houses;
    """
    )
    suspend fun getHouses(): List<HouseDto>

    @Query(
        """
        DELETE FROM houses;
    """
    )
    suspend fun removeHouses()

    @Query(
        """
        SELECT * FROM houses LIMIT 1;
    """
    )
    suspend fun getFirstHouse(): HouseDto?
}