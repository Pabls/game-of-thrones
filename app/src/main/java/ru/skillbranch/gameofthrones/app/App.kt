package ru.skillbranch.gameofthrones.app

import android.app.Application
import ru.skillbranch.gameofthrones.app.di.components.ApplicationComponent
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent

class App : Application() {
    companion object {
        lateinit var applicationComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = ApplicationComponent(this)
    }
}