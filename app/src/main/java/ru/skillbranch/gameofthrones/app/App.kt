package ru.skillbranch.gameofthrones.app

import android.app.Application
import ru.skillbranch.gameofthrones.app.di.components.ApplicationComponent
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent
import com.facebook.stetho.Stetho



class App : Application() {
    companion object {
        lateinit var applicationComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = ApplicationComponent(this)
        initStetho()
    }

    private fun initStetho() {
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}