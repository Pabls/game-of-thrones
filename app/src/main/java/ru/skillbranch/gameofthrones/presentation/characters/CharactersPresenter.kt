package ru.skillbranch.gameofthrones.presentation.characters

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter

class CharactersPresenter : BasePresenter<ICharactersView>() {

    override fun attachView(view: ICharactersView?) {
        super.attachView(view)

        val characters = arrayListOf<CharacterItem>(
            CharacterItem(
                id = "1",
                aliases = listOf("don of winter", "jimmy"),
                house = "Koko",
                name = "Tui",
                titles = listOf("lord of the ring", "billy bons")
            ),
            CharacterItem(
                id = "2",
                aliases = listOf("winny", "mister A"),
                house = "Koko",
                name = "Jim",
                titles = listOf("Quin", "King")
            )
        )

        getView()?.setData(characters)
    }

    fun onItemClick(id: String) {
        getView()?.navigateToCharacterFragment(id)
    }
}