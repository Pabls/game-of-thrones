package ru.skillbranch.gameofthrones.app.di.modules

import android.content.Context
import ru.skillbranch.gameofthrones.data.database.AppDatabase
import ru.skillbranch.gameofthrones.data.network.Api
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository
import ru.skillbranch.gameofthrones.repositories.ResourcesRepository
import ru.skillbranch.gameofthrones.repositories.RootRepository

class RepositoriesModule(private val context: Context, private val api: Api, private val database: AppDatabase) {
    init {
        ResourcesRepository.setContext(context)
        RootRepository.setApi(api)
        RootRepository.setDatabase(database)
    }

    fun getRootRepository(): IRootRepository = RootRepository
    fun getResourcesRepository(): IResourcesRepository = ResourcesRepository
}