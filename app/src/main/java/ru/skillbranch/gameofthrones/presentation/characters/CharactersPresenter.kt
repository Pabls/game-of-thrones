package ru.skillbranch.gameofthrones.presentation.characters

import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class CharactersPresenter(
    private val rootRepository: IRootRepository,
    private val resourcesRepository: IResourcesRepository
) : BasePresenter<ICharactersView>() {

    override fun attachView(view: ICharactersView?) {
        super.attachView(view)
        getCharacters()
    }

    fun getCharacters() {
        rootRepository.findCharactersByHouseName(getView()?.getHouse()!!) {
            getView()?.setData(it)
        }
    }

    fun onItemClick(id: String) {
        getView()?.navigateToCharacterFragment(id)
    }
}