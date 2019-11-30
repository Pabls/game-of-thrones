package ru.skillbranch.gameofthrones.presentation.character

import android.os.Bundle
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseFragment
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

class CharacterFragment : BaseFragment(), IBaseView {

    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private var param1: String? = null
    private var presenter: CharacterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun inject() {
        getComponent().inject(this)
    }

    override fun setPresenter(presenter: IPresenter<IBaseView>) {
        this.presenter = presenter as CharacterPresenter
    }

    override fun getLayoutId(): Int = R.layout.fragment_character

    override fun getPresenter(): IPresenter<IBaseView>? {
        return try {
            return if (presenter is BasePresenter<*>)
                presenter as BasePresenter<IBaseView>
            else null
        } catch (ex: Exception) {
            null
        }
    }
}
