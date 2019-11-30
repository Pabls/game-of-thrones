package ru.skillbranch.gameofthrones.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.app.App
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent

abstract class BaseFragment : Fragment(), IBaseView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        getPresenter()?.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
    abstract fun setPresenter(presenter: IPresenter<IBaseView>)
    abstract fun getLayoutId(): Int
    protected abstract fun getPresenter(): IPresenter<IBaseView>?
}