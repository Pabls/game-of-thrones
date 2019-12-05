package ru.skillbranch.gameofthrones.presentation.splash

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseActivity
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter
import ru.skillbranch.gameofthrones.presentation.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask


class SplashActivity : BaseActivity(), ISplashView {

    private var presenter: SplashPresenter? = null
    private var backgroundImage: Drawable? = null
    private val timer = Timer()

    private var red = 255
    private var green = 255
    private var blue = 255

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
        backgroundImage = window?.decorView?.background
    }

    override fun onStart() {
        super.onStart()
        backgroundImage?.setColorFilter(Color.rgb(red, green ,blue), PorterDuff.Mode.MULTIPLY)
    }

    override fun showLoading() {
        timer.schedule(timerTask {
            this@SplashActivity.runOnUiThread {
                backgroundImage?.setColorFilter(Color.rgb(red, green, blue), PorterDuff.Mode.MULTIPLY)
                red -= 2
                green -= 1
                blue -= 4
            }
        }, 0L, 50L)
    }

    override fun hideLoading() {
        cancelTimer()
    }

    override fun navigateToMainScreen() {
        val intent = Intent(MainActivity.getStartIntent(this))
        startActivity(intent)
    }

    private fun cancelTimer() {
        timer.cancel()
    }
}
