package ru.skillbranch.gameofthrones.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.app.App
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent
import ru.skillbranch.gameofthrones.presentation.main.IRouter

abstract class BaseFragment : Fragment(), IBaseView {

    private var progress: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        progress = activity?.findViewById(R.id.v_progress)
        getPresenter()?.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter()?.detachView()
    }

    override fun showLoading() {
        progress?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress?.visibility = View.GONE
    }

    override fun showMessage(message: String, indefinite: Boolean) {
        getBaseActivity()?.showMessage(message, indefinite)
    }

    override fun showError(message: String, indefinite: Boolean) {
        getBaseActivity()?.showError(message, indefinite)
    }

    override fun showMessageWithAction(message: String, actionTitle: String, indefinite: Boolean, action: () -> Unit) {
        getBaseActivity()?.showMessageWithAction(message, actionTitle, indefinite, action)
    }

    fun hideMessage() {
        getBaseActivity()?.hideMessage()
    }

    protected fun getComponent(): IApplicationComponent = App.applicationComponent

    protected abstract fun inject()
    abstract fun setPresenter(presenter: IPresenter<IBaseView>)
    abstract fun getLayoutId(): Int
    protected abstract fun getPresenter(): IPresenter<IBaseView>?

    protected fun getRouter(): IRouter? {
        return if (activity != null && activity is IRouter) {
            activity as IRouter
        } else {
            null
        }
    }

    private fun getBaseActivity(): BaseActivity? {
        return if (activity != null && activity is BaseActivity) {
            activity as BaseActivity
        } else {
            null
        }
    }
}