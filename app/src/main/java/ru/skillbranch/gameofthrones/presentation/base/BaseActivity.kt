package ru.skillbranch.gameofthrones.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ru.skillbranch.gameofthrones.app.App
import ru.skillbranch.gameofthrones.app.di.components.IApplicationComponent
import android.view.View


abstract class BaseActivity : AppCompatActivity(), IBaseView {

    private var rootLayout: View? = null
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(getLayoutId())
        getPresenter()?.attachView(this)
        rootLayout = findViewById(android.R.id.content)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.detachView()
    }

    override fun showMessage(message: String, indefinite: Boolean) {
        createAndShowSnackbar(
            message = message,
            bgColor = ru.skillbranch.gameofthrones.R.color.color_primary_dark,
            indefinite = indefinite
        )
    }

    override fun showError(message: String, indefinite: Boolean) {
        createAndShowSnackbar(
            message = message,
            bgColor = ru.skillbranch.gameofthrones.R.color.color_accent,
            indefinite = indefinite
        )
    }

    override fun showMessageWithAction(message: String, actionTitle: String, indefinite: Boolean, action: () -> Unit) {
        createAndShowSnackbar(message = message, actionTitle = actionTitle, action = action, indefinite = true)
    }

    fun hideMessage() {
        snackbar?.dismiss()
    }

    protected fun getComponent(): IApplicationComponent = App.applicationComponent

    protected abstract fun inject()
    protected abstract fun getLayoutId(): Int
    protected abstract fun getPresenter(): IPresenter<IBaseView>?
    abstract fun setPresenter(presenter: BasePresenter<IBaseView>)

    private fun createAndShowSnackbar(
        message: String,
        bgColor: Int? = null,
        actionTitle: String? = null,
        indefinite: Boolean,
        action: (() -> Unit)? = null
    ) {
        if (rootLayout != null) {
            snackbar =
                if (indefinite)
                    getSnackbar(message, Snackbar.LENGTH_INDEFINITE)
                else getSnackbar(message, Snackbar.LENGTH_LONG)

            if (bgColor != null) {
                snackbar?.view?.setBackgroundColor(ContextCompat.getColor(rootLayout!!.context, bgColor))
            }

            if (actionTitle != null && action != null) {
                snackbar?.setAction(actionTitle) { action.invoke() }
                snackbar?.setActionTextColor(
                    ContextCompat.getColor(
                        rootLayout!!.context,
                        ru.skillbranch.gameofthrones.R.color.color_primary_dark
                    )
                )
            }

            snackbar?.show()
        }
    }

    private fun getSnackbar(message: String, length: Int): Snackbar =
        Snackbar.make(rootLayout!!, message, length)
}