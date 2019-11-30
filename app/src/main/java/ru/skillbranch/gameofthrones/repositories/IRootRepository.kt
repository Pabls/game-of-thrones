package ru.skillbranch.gameofthrones.repositories

import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface IRootRepository {
    fun getAllHouses(result : (houses : List<HouseRes>) -> Unit)
    fun getNeedHouses(vararg houseNames: String, result : (houses : List<HouseRes>) -> Unit)
    fun getNeedHouseWithCharacters(vararg houseNames: String, result : (houses : List<Pair<HouseRes, List<CharacterRes>>>) -> Unit)
    fun insertHouses(houses : List<HouseRes>, complete: () -> Unit)
    fun insertCharacters(Characters : List<CharacterRes>, complete: () -> Unit)
    fun dropDb(complete: () -> Unit)
    fun findCharactersByHouseName(name : String, result: (Characters : List<CharacterItem>) -> Unit)
    fun findCharacterFullById(id : String, result: (Character : CharacterFull) -> Unit)
    fun isNeedUpdate(result: (isNeed : Boolean) -> Unit)
}