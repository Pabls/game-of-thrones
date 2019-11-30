package ru.skillbranch.gameofthrones.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.gameofthrones.app.App
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent

abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(getLayoutId())
        getPresenter()?.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.detachView()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessageWithAction(message: String, actionTitle: String, action: () -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected fun getComponent(): IApplicationComponent = App.applicationComponent

    protected abstract fun inject()
    abstract fun setPresenter(presenter: BasePresenter<IBaseView>)
    protected abstract fun getLayoutId(): Int
    protected abstract fun getPresenter(): IPresenter<IBaseView>?
}