package ru.skillbranch.gameofthrones.presentation.splash

import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class SplashPresenter(
    private val rootRepository: IRootRepository,
    private val resourcesRepository: IResourcesRepository
) : BasePresenter<ISplashView>() {

    override fun attachView(view: ISplashView?) {
        super.attachView(view)

        rootRepository.getNeedHouseWithCharacters(houseNames = *AppConfig.NEED_HOUSES ) {
            houses ->  getView()?.navigateToMainScreen()
        }
    }
}