package ru.skillbranch.gameofthrones.repositories

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.skillbranch.gameofthrones.data.database.AppDatabase
import ru.skillbranch.gameofthrones.data.database.entities.CharacterDto
import ru.skillbranch.gameofthrones.data.database.entities.HouseDto
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.network.Api
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

object RootRepository : IRootRepository {

    private val FIRST_PAGE = 1

    private lateinit var api: Api
    private lateinit var database: AppDatabase

    fun setApi(api: Api) {
        this.api = api
    }

    fun setDatabase(database: AppDatabase) {
        this.database = database
    }

    /**
     * Получение данных о всех домах
     * @param result - колбек содержащий в себе список данных о домах
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun getAllHouses(result: (houses: List<HouseRes>) -> Unit) {
        var housesRes: MutableList<HouseRes> = ArrayList()
        tryGetAllHouses(FIRST_PAGE, housesRes, result)
    }

    /**
     * Получение данных о требуемых домах по их полным именам (например фильтрация всех домов)
     * @param houseNames - массив полных названий домов (смотри AppConfig)
     * @param result - колбек содержащий в себе список данных о домах
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun getNeedHouses(vararg houseNames: String, result: (houses: List<HouseRes>) -> Unit) {
        getAllHouses { houses ->
            if (houses.isNotEmpty()) {
                val needHouses = houses.filter { house -> houseNames.contains(house.name) }
                result.invoke(needHouses)
            } else {
                result.invoke(houses)
            }
        }
    }

    /**
     * Получение данных о требуемых домах по их полным именам и персонажах в каждом из домов
     * @param houseNames - массив полных названий домов (смотри AppConfig)
     * @param result - колбек содержащий в себе список данных о доме и персонажей в нем (Дом - Список Персонажей в нем)
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun getNeedHouseWithCharacters(
        vararg houseNames: String,
        result: (houses: List<Pair<HouseRes, List<CharacterRes>>>) -> Unit
    ) {
        getNeedHouses(houseNames = *houseNames) { houses ->
            if (houses.isNotEmpty()) {
                var characterRes: MutableList<CharacterRes> = ArrayList()
                tryGetNeedCharacters(FIRST_PAGE, characterRes) { characters ->
                    val pairs = mutableListOf<Pair<HouseRes, List<CharacterRes>>>()
                    val chars = mutableListOf<CharacterRes>()
                    houses.forEach { house ->
                        val ch = characterRes.filter { it ->
                            house.swornMembers.contains(it.url)
                        }
                        chars.addAll(ch)
                        pairs.add(house to ch)
                    }

                    insertHouses(houses) {
                        insertCharacters(chars) {
                            result.invoke(pairs)
                        }
                    }
                }
            } else {
                result.invoke(listOf<Pair<HouseRes, List<CharacterRes>>>())
            }
        }
    }

    /**
     * Запись данных о домах в DB
     * @param houses - Список персонажей (модель HouseRes - модель ответа из сети)
     * необходимо произвести трансформацию данных
     * @param complete - колбек о завершении вставки записей db
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun insertHouses(houses: List<HouseRes>, complete: () -> Unit) {
        val job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val dtos = houses.map { it ->
                    HouseDto(
                        shortName = it.name.substringAfter(" ").substringBefore(" "),
                        name = it.name,
                        coatOfArms = it.coatOfArms,
                        currentLord = it.currentLord,
                        diedOut = it.diedOut,
                        founded = it.founded,
                        heir = it.heir,
                        founder = it.founder,
                        region = it.region,
                        url = it.url,
                        words = it.words,
                        titles = it.titles,
                        ancestralWeapons = it.ancestralWeapons,
                        cadetBranches = it.cadetBranches,
                        overlord = it.overlord,
                        seats = it.seats,
                        swornMembers = it.swornMembers
                    )
                }
                database.getHouseDao().insertHouses(dtos)
                complete.invoke()
            }
        }
    }

    /**
     * Запись данных о пересонажах в DB
     * @param Characters - Список персонажей (модель CharacterRes - модель ответа из сети)
     * необходимо произвести трансформацию данных
     * @param complete - колбек о завершении вставки записей db
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun insertCharacters(Characters: List<CharacterRes>, complete: () -> Unit) {
        val job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val dtos = Characters.map { it ->
                    CharacterDto(
                        url = it.url,
                        titles = it.titles,
                        name = it.name,
                        mother = it.mother,
                        father = it.father,
                        aliases = it.aliases,
                        allegiances = it.allegiances,
                        books = it.books,
                        born = it.born,
                        culture = it.culture,
                        died = it.died,
                        gender = it.gender,
                        playedBy = it.playedBy,
                        povBooks = it.povBooks,
                        spouse = it.spouse,
                        tvSeries = it.tvSeries
                    )
                }
                database.getCharactersDao().insertCharacters(dtos)
                complete.invoke()
            }
        }
    }

    /**
     * При вызове данного метода необходимо выполнить удаление всех записей в db
     * @param complete - колбек о завершении очистки db
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun dropDb(complete: () -> Unit) {
        val job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                database.getHouseDao().removeHouses()
                complete.invoke()
            }
        }
    }

    /**
     * Поиск всех персонажей по имени дома, должен вернуть список краткой информации о персонажах
     * дома - смотри модель CharacterItem
     * @param name - краткое имя дома (его первычный ключ)
     * @param result - колбек содержащий в себе список краткой информации о персонажах дома
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun findCharactersByHouseName(name: String, result: (Characters: List<CharacterItem>) -> Unit) {
        //TODO implement me
    }

    /**
     * Поиск персонажа по его идентификатору, должен вернуть полную информацию о персонаже
     * и его родственных отношения - смотри модель CharacterFull
     * @param id - идентификатор персонажа
     * @param result - колбек содержащий в себе полную информацию о персонаже
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override fun findCharacterFullById(id: String, result: (Character: CharacterFull) -> Unit) {
        //TODO implement me
    }

    /**
     * Метод возвращет true если в базе нет ни одной записи, иначе false
     * @param result - колбек о завершении очистки db
     */
    override fun isNeedUpdate(result: (isNeed: Boolean) -> Unit) {
        val job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val house = database.getHouseDao().getFirstHouse()
                result.invoke(house == null)
            }
        }
    }

    private fun tryGetAllHouses(
        page: Int,
        houses: MutableList<HouseRes>,
        result: (houses: List<HouseRes>) -> Unit
    ) {
        api?.getHouses(page).enqueue(object : Callback<List<HouseRes>> {
            override fun onFailure(call: Call<List<HouseRes>>, t: Throwable) {
                result.invoke(listOf())
            }

            override fun onResponse(call: Call<List<HouseRes>>, response: Response<List<HouseRes>>) {
                if (response.body() != null) {
                    val housesRes = response.body()!!
                    if (housesRes.isNotEmpty()) {
                        houses.addAll(housesRes)
                        tryGetAllHouses(page = page + 1, houses = houses, result = result)
                    } else {
                        result.invoke(houses)
                    }
                }
            }
        })
    }

    private fun tryGetNeedCharacters(
        page: Int,
        characters: MutableList<CharacterRes>,
        result: (houses: List<CharacterRes>) -> Unit
    ) {
        api?.getCharacters(page).enqueue(object : Callback<List<CharacterRes>> {
            override fun onFailure(call: Call<List<CharacterRes>>, t: Throwable) {
                result.invoke(listOf())
            }

            override fun onResponse(call: Call<List<CharacterRes>>, response: Response<List<CharacterRes>>) {
                if (response.body() != null) {
                    val charactersRes = response.body()!!
                    if (charactersRes.isNotEmpty()) {
                        characters.addAll(charactersRes)
                        tryGetNeedCharacters(page = page + 1, characters = characters, result = result)
                    } else {
                        result.invoke(characters)
                    }
                }
            }
        })
    }
}