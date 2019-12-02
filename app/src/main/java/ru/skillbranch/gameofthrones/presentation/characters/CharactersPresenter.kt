package ru.skillbranch.gameofthrones.presentation.characters

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class CharactersPresenter(
    private val rootRepository: IRootRepository,
    private val resourcesRepository: IResourcesRepository
) : BasePresenter<ICharactersView>() {

    private var characters: List<CharacterItem> = ArrayList()

    override fun attachView(view: ICharactersView?) {
        super.attachView(view)
        getCharacters()
    }

    fun getCharacters() {
        rootRepository.findCharactersByHouseName(getView()?.getHouse()!!) {
            this.characters = it
            getView()?.setData(it)
        }
    }

    fun onItemClick(id: String) {
        getView()?.navigateToCharacterFragment(id)
    }

    fun searchCharacter(query: String?) {
        if (query.isNullOrEmpty()) {
            getView()?.setData(characters)
        } else {
            getView()?.setData(characters.filter { it.name.contains(query, true) })
        }
    }
}