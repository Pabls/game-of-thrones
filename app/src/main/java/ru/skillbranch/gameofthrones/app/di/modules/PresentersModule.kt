package ru.skillbranch.gameofthrones.app.di.modules

import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.character.CharacterPresenter
import ru.skillbranch.gameofthrones.presentation.characters.CharactersPresenter
import ru.skillbranch.gameofthrones.presentation.splash.SplashPresenter

class PresentersModule(private val repositoriesModule: RepositoriesModule) {

    fun getSplashPresenter(): BasePresenter<IBaseView> =
        SplashPresenter(
            repositoriesModule.getRootRepository(),
            repositoriesModule.getResourcesRepository()
        ) as BasePresenter<IBaseView>

    fun getCharacterPresenter(): BasePresenter<IBaseView> =
        CharacterPresenter(
            repositoriesModule.getRootRepository(),
            repositoriesModule.getResourcesRepository()
        ) as BasePresenter<IBaseView>

    fun getCharactersPresenter(): BasePresenter<IBaseView> = CharactersPresenter(
        repositoriesModule.getRootRepository(),
        repositoriesModule.getResourcesRepository()
    ) as BasePresenter<IBaseView>
}