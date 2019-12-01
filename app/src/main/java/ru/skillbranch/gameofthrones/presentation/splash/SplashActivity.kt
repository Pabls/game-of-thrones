package ru.skillbranch.gameofthrones.presentation.splash

import android.content.Intent
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
    //private var backgroundImage: Drawable? = null

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
        //backgroundImage = window?.decorView?.background
        //backgroundImage?.setColorFilter(Color.rgb(140, 140 ,140), PorterDuff.Mode.MULTIPLY)
    }

//    override fun onStart() {
//        super.onStart()
////        var red = 140
////        var green = 140
////        var blue = 140
////        Timer().schedule(timerTask {
////            this@SplashActivity.runOnUiThread {
////                backgroundImage?.setColorFilter(Color.rgb(red, green, blue), PorterDuff.Mode.MULTIPLY)
////                red += 5
////                green += 5
////                blue -= 5
////            }
////        }, 0L, 200L)
////        Thread(Runnable {
////            Thread.sleep(5000)
////            this.runOnUiThread {
////                startMainActivity()
////            }
////        }).start()
//    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToMainScreen() {
        val intent = Intent(MainActivity.getStartIntent(this))
        startActivity(intent)
    }

    private fun startMainActivity() {
        val intent = Intent(MainActivity.getStartIntent(this))
        startActivity(intent)
    }
}
