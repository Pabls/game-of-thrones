package ru.skillbranch.gameofthrones.presentation.splash

import android.content.Intent
import android.os.Bundle
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseActivity
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter
import ru.skillbranch.gameofthrones.presentation.main.MainActivity


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

    override fun onStart() {
        super.onStart()
        Thread(Runnable {
            Thread.sleep(1000)
            this.runOnUiThread {
                startMainActivity()
            }
        }).start()
    }

    private fun startMainActivity() {
        val intent = Intent(MainActivity.getStartIntent(this))
        startActivity(intent)
    }
}
