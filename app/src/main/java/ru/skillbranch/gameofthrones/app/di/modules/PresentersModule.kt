package ru.skillbranch.gameofthrones.app.di.modules

import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.character.CharacterPresenter
import ru.skillbranch.gameofthrones.presentation.characters.CharactersPresenter
import ru.skillbranch.gameofthrones.presentation.splash.SplashPresenter

class PresentersModule {
    fun getSplashPresenter(): BasePresenter<IBaseView> = SplashPresenter() as BasePresenter<IBaseView>
    fun getCharacterPresenter(): BasePresenter<IBaseView> = CharacterPresenter() as BasePresenter<IBaseView>
    fun getCharactersPresenter(): BasePresenter<IBaseView> = CharactersPresenter() as BasePresenter<IBaseView>
}