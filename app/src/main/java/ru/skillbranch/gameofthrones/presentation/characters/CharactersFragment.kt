package ru.skillbranch.gameofthrones.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.presentation.base.BaseFragment
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter
import ru.skillbranch.gameofthrones.presentation.characters.adapter.CharactersAdapter
import ru.skillbranch.gameofthrones.presentation.characters.adapter.ItemClickListener
import ru.skillbranch.gameofthrones.presentation.main.IRouter


class CharactersFragment : BaseFragment(), ICharactersView {

    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String) =
            CharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private var param1: String? = null
    private var presenter: CharactersPresenter? = null
    private var rvCharacters: RecyclerView? = null
    private var adapter: CharactersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun inject() {
        getComponent().inject(this)
    }

    override fun setPresenter(presenter: IPresenter<IBaseView>) {
        this.presenter = presenter as CharactersPresenter
    }

    override fun getLayoutId(): Int = R.layout.fragment_characters

    override fun getPresenter(): IPresenter<IBaseView>? {
        return try {
            return if (presenter is BasePresenter<*>)
                presenter as BasePresenter<IBaseView>
            else null
        } catch (ex: Exception) {
            null
        }
    }

    override fun setData(characters: List<CharacterItem>) {
        adapter?.setData(characters)
    }

    override fun navigateToCharacterFragment(id: String) {
        getRouter()?.navigateToCharacterFragment(id)
    }

    private fun initView(view: View) {
        rvCharacters = view.findViewById(R.id.rv_characters)
        adapter = CharactersAdapter(object : ItemClickListener {
            override fun onClick(id: String) {
                presenter?.onItemClick(id)
            }
        })
        rvCharacters?.adapter = adapter
    }
}
