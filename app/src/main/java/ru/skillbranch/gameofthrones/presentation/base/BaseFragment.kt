package ru.skillbranch.gameofthrones.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.app.App
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent
import ru.skillbranch.gameofthrones.presentation.main.IRouter

abstract class BaseFragment : Fragment(), IBaseView {

    private var rootLayout: View? = null
    private var progress: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        rootLayout = activity?.findViewById(android.R.id.content)
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
        createAndShowSnackbar(message = message, bgColor = R.color.color_primary_dark, indefinite = indefinite)
    }

    override fun showError(message: String, indefinite: Boolean) {
        createAndShowSnackbar(message = message, bgColor = R.color.color_accent, indefinite = indefinite)
    }

    override fun showMessageWithAction(message: String, actionTitle: String, indefinite: Boolean, action: () -> Unit) {
        createAndShowSnackbar(message = message, actionTitle = actionTitle, indefinite = indefinite, action = action)
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

    private fun createAndShowSnackbar(
        message: String,
        bgColor: Int? = null,
        actionTitle: String? = null,
        indefinite: Boolean,
        action: (() -> Unit)? = null
    ) {
        if (rootLayout != null) {
            val snackbar = if (indefinite)
                getSnackbar(message, Snackbar.LENGTH_INDEFINITE)
            else getSnackbar(message, Snackbar.LENGTH_LONG)

            if (bgColor != null) {
                snackbar.view.setBackgroundColor(ContextCompat.getColor(rootLayout!!.context, bgColor))
            }

            if (actionTitle != null && action != null) {
                snackbar.setAction(actionTitle) { action.invoke() }
                snackbar.setActionTextColor(ContextCompat.getColor(rootLayout!!.context, R.color.color_primary_dark))
            }

            snackbar.show()
        }
    }

    private fun getSnackbar(message: String, length: Int): Snackbar =
        Snackbar.make(rootLayout!!, message, length)
}