package ru.skillbranch.gameofthrones.presentation.character

import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.RelativeCharacter
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class CharacterPresenter(
    private val rootRepository: IRootRepository,
    private val resourcesRepository: IResourcesRepository
) : BasePresenter<ICharacterView>() {

    lateinit var char: CharacterFull

    override fun attachView(view: ICharacterView?) {
        super.attachView(view)
        getView()?.showLoading()
        rootRepository.findCharacterFullById(getView()?.getCharacterId()!!) {
            char = it
            setCharacterData()
        }
    }

    fun onFatherBtnClick() {
        getView()?.showNextCharacterScreen(char.father!!.id)
    }

    fun onMotherBtnClick() {
        getView()?.showNextCharacterScreen(char.mother!!.id)
    }

    private fun setCharacterData() {
        val colors = AppConfig.getColorsByHome(char.house)
        getView()?.setImage(AppConfig.getIconIdByHome(char.house, true))
        getView()?.setColor(colors.second, colors.third)

        getView()?.setAliases(char.aliases.joinToString(separator = "\t"))
        getView()?.setTitles(char.titles.joinToString(separator = "\t"))
        getView()?.setBorn(char.born)
        getView()?.setWords(char.words)
        getView()?.setName(char.name)

        if (char.father != null)
            getView()?.showFatherButton(char.father!!.name)

        if (char.mother != null)
            getView()?.showMotherButton(char.mother!!.name)

        if (!char.died.isNullOrEmpty())
            getView()?.showDiedMessage(char.died)

        getView()?.hideLoading()
    }
}