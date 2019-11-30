package ru.skillbranch.gameofthrones.app.di.components

import ru.skillbranch.gameofthrones.app.di.modules.PresentersModule
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import ru.skillbranch.gameofthrones.presentation.splash.SplashActivity

class ApplicationComponent : IApplicationComponent {

    val presentersModule = PresentersModule()

    override fun inject(characterFragment: CharacterFragment) {
        characterFragment.setPresenter(presentersModule.getCharacterPresenter())
    }

    override fun inject(charactersFragment: CharactersFragment) {
        charactersFragment.setPresenter(presentersModule.getCharactersPresenter())
    }

    override fun inject(splashActivity: SplashActivity) {
        splashActivity.setPresenter(presentersModule.getSplashPresenter())
    }
}