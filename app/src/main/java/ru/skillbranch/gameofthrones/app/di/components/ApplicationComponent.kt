package ru.skillbranch.gameofthrones.app.di.components

import android.content.Context
import ru.skillbranch.gameofthrones.app.di.modules.*
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import ru.skillbranch.gameofthrones.presentation.main.MainActivity
import ru.skillbranch.gameofthrones.presentation.splash.SplashActivity

class ApplicationComponent(private val context: Context) : IApplicationComponent {

    private var presentersModule = PresentersModule(context)

    override fun inject(mainActivity: MainActivity) {
        mainActivity.setPresenter(presentersModule.provideMainPresenter())
    }

    override fun inject(characterFragment: CharacterFragment) {
        characterFragment.setPresenter(presentersModule.provideCharacterPresenter())
    }

    override fun inject(charactersFragment: CharactersFragment) {
        charactersFragment.setPresenter(presentersModule.provideCharactersPresenter())
    }

    override fun inject(splashActivity: SplashActivity) {
        splashActivity.setPresenter(presentersModule.provideSplashPresenter())
    }
}