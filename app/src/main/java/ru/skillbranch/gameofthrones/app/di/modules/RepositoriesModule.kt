package ru.skillbranch.gameofthrones.app.di.modules

import android.content.Context
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository
import ru.skillbranch.gameofthrones.repositories.ResourcesRepository
import ru.skillbranch.gameofthrones.repositories.RootRepository

class RepositoriesModule(private val context: Context) {
    init {
        ResourcesRepository.context = context
    }

    fun getRootRepository(): IRootRepository = RootRepository
    fun getResourcesRepository(): IResourcesRepository = ResourcesRepository
}