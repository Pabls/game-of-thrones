package ru.skillbranch.gameofthrones.app.di.components

import android.content.Context
import ru.skillbranch.gameofthrones.app.di.modules.HelpersModule
import ru.skillbranch.gameofthrones.app.di.modules.NetworkModule
import ru.skillbranch.gameofthrones.app.di.modules.PresentersModule
import ru.skillbranch.gameofthrones.app.di.modules.RepositoriesModule
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import ru.skillbranch.gameofthrones.presentation.splash.SplashActivity

class ApplicationComponent(private val context: Context) : IApplicationComponent {

    val helpersModule = HelpersModule()
    val networkModule = NetworkModule(helpersModule.getGson())
    val repositoriesModule = RepositoriesModule(context, networkModule.getApi())
    val presentersModule = PresentersModule(repositoriesModule)

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