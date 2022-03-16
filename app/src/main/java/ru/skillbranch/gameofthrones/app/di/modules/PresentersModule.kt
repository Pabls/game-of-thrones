package ru.skillbranch.gameofthrones.app.di.modules

import android.content.Context
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.character.CharacterPresenter
import ru.skillbranch.gameofthrones.presentation.characters.CharactersPresenter
import ru.skillbranch.gameofthrones.presentation.main.MainPresenter
import ru.skillbranch.gameofthrones.presentation.splash.SplashPresenter

class PresentersModule(private val context: Context) {

    val repositoriesModule = RepositoriesModule(context)

    fun provideSplashPresenter(): BasePresenter<IBaseView> =
        SplashPresenter(
            repositoriesModule.provideRootRepository(),
            repositoriesModule.provideResourcesRepository()
        ) as BasePresenter<IBaseView>

    fun provideMainPresenter(): BasePresenter<IBaseView> =
        MainPresenter() as BasePresenter<IBaseView>

    fun provideCharacterPresenter(): BasePresenter<IBaseView> =
        CharacterPresenter(
            repositoriesModule.provideRootRepository(),
            repositoriesModule.provideResourcesRepository()
        ) as BasePresenter<IBaseView>

    fun provideCharactersPresenter(): BasePresenter<IBaseView> = CharactersPresenter(
        repositoriesModule.provideRootRepository(),
        repositoriesModule.provideResourcesRepository()
    ) as BasePresenter<IBaseView>
}