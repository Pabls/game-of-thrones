package ru.skillbranch.gameofthrones.presentation.character

import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.RelativeCharacter
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter

class CharacterPresenter : BasePresenter<ICharacterView>() {

    override fun attachView(view: ICharacterView?) {
        super.attachView(view)

        val char = CharacterFull(
            "1",
            "Jim Carry",
            "AAA its my time",
            "12.09.1987",
            "13.09.1999",
            titles = listOf("lord of the ring", "billy bons"),
            aliases = listOf("don of winter", "jimmy"),
            house = "Winterfall",
            father = RelativeCharacter("32", "Jonson Artur King", "Winterfall"),
            mother = RelativeCharacter("33", "Jane Arturus King", "Winterfall")
        )

        getView()?.setColor(R.color.stark_primary, R.color.stark_accent)

        getView()?.setAliases(char.aliases.joinToString(separator = "\t"))
        getView()?.setTitles(char.titles.joinToString(separator = "\t"))
        getView()?.setBorn(char.born)
        getView()?.setWords(char.words)

        if (char.father != null)
            getView()?.showFatherButton(char.father.name)

        if (char.mother != null)
            getView()?.showMotherButton(char.mother.name)

        if (!char.died.isNullOrEmpty())
            getView()?.showDiedMessage(char.died)
    }
}