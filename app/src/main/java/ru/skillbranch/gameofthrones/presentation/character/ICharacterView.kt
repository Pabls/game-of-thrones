package ru.skillbranch.gameofthrones.presentation.character

import ru.skillbranch.gameofthrones.presentation.base.IBaseView

interface ICharacterView : IBaseView {
    fun setColor(primaryColorRes: Int, accentColorRes: Int)
    fun setWords(words: String)
    fun setBorn(born: String)
    fun setTitles(titles: String)
    fun setAliases(aliases: String)
    fun showFatherButton(father: String)
    fun showMotherButton(mother: String)
    fun showDiedMessage(date: String)
    fun showNextCharacterScreen(id: String)
}