package ru.skillbranch.gameofthrones.presentation.characters

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.presentation.base.IBaseView

interface ICharactersView : IBaseView {
    fun setData(characters: List<CharacterItem>)
    fun navigateToCharacterFragment(id: String)
    fun getHouse(): String?
}