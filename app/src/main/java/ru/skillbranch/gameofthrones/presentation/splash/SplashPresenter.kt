package ru.skillbranch.gameofthrones.presentation.splash

import ru.skillbranch.gameofthrones.utils.AppConfig
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.repositories.IResourcesRepository
import ru.skillbranch.gameofthrones.repositories.IRootRepository

class SplashPresenter(
    private val rootRepository: IRootRepository,
    private val resourcesRepository: IResourcesRepository
) : BasePresenter<ISplashView>() {

    override fun attachView(view: ISplashView?) {
        super.attachView(view)
        getView()?.showLoading()
        checkDatabase()
    }

    private fun checkDatabase() {
        rootRepository.isNeedUpdate { isNeed ->
            if (isNeed) {
                getDataFromApi()
            } else {
                navigateToMainScreen()
            }
        }
    }

    private fun getDataFromApi() {
        rootRepository.getNeedHouseWithCharacters(houseNames = *AppConfig.NEED_HOUSES) { navigateToMainScreen() }
    }

    private fun navigateToMainScreen() {
        getView()?.hideLoading()
        getView()?.navigateToMainScreen()
    }
}