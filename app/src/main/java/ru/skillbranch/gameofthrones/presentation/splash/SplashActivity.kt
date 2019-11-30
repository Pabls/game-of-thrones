package ru.skillbranch.gameofthrones.presentation.splash

import android.os.Bundle
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseActivity
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter


class SplashActivity : BaseActivity() {

    private var presenter: SplashPresenter? = null

    override fun setPresenter(presenter: BasePresenter<IBaseView>) {
        this.presenter = presenter as SplashPresenter
    }

    override fun inject() {
        getComponent().inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getPresenter(): IPresenter<IBaseView>? {
        return try {
            return if (presenter is BasePresenter<*>)
                presenter as BasePresenter<IBaseView>
            else null
        } catch (ex: Exception) {
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
