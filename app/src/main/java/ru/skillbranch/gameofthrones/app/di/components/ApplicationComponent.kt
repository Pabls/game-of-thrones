package ru.skillbranch.gameofthrones.app.di.components

import ru.skillbranch.gameofthrones.app.di.modules.PresentersModule
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import ru.skillbranch.gameofthrones.presentation.splash.SplashActivity

class ApplicationComponent: IApplicationComponent {

    val presentersModule = PresentersModule()

    override fun inject(characterFragment: CharacterFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(charactersFragment: CharactersFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(splashActivity: SplashActivity) {
        splashActivity.setPresenter(presentersModule.getSplashPresenter())
    }
}