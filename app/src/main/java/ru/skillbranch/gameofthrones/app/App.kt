package ru.skillbranch.gameofthrones.app

import android.app.Application
import ru.skillbranch.gameofthrones.app.di.components.ApplicationComponent
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent

class App : Application() {
    companion object {
        val applicationComponent: IApplicationComponent = ApplicationComponent()
    }
}