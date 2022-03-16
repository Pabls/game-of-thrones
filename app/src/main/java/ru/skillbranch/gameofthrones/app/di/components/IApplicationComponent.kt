package ru.skillbranch.gameofthrones.app.di.components

import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import ru.skillbranch.gameofthrones.presentation.main.MainActivity
import ru.skillbranch.gameofthrones.presentation.splash.SplashActivity

interface IApplicationComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(characterFragment: CharacterFragment)
    fun inject(charactersFragment: CharactersFragment)
}