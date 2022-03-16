package ru.skillbranch.gameofthrones.app.di.modules

import android.content.Context
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository
import ru.skillbranch.gameofthrones.repositories.ResourcesRepository
import ru.skillbranch.gameofthrones.repositories.RootRepository

class RepositoriesModule(private val context: Context) {

    val databaseModule = DatabaseModule(context)
    val networkModule = NetworkModule()

    init {
        ResourcesRepository.setContext(context)
        RootRepository.setApi(networkModule.provideApi())
        RootRepository.setDatabase(databaseModule.provideDataBase())
    }

    fun provideRootRepository(): IRootRepository = RootRepository
    fun provideResourcesRepository(): IResourcesRepository = ResourcesRepository
}