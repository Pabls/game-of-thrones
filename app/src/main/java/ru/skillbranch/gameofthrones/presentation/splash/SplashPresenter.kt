package ru.skillbranch.gameofthrones.presentation.splash

import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class SplashPresenter(
    val rootRepository: IRootRepository,
    val resourcesRepository: IResourcesRepository
) : BasePresenter<ISplashView>() {

    override fun attachView(view: ISplashView?) {
        super.attachView(view)
    }
}