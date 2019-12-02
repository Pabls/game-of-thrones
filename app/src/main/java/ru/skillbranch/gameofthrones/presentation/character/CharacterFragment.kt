package ru.skillbranch.gameofthrones.presentation.character

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseFragment
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

class CharacterFragment : BaseFragment(), ICharacterView {

    companion object {

        private const val ARG_ID = "ARG_ID"

        @JvmStatic
        fun newInstance(characterId: String) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, characterId)
                }
            }
    }

    private var characterId: String? = null
    private var presenter: CharacterPresenter? = null
    private var tvWords: TextView? = null
    private var tvBorn: TextView? = null
    private var tvTitles: TextView? = null
    private var tvAliases: TextView? = null
    private var btnFather: Button? = null
    private var btnMother: Button? = null
    private var tvFatherTitle: TextView? = null
    private var tvMotherTitle: TextView? = null
    private var ivBack: ImageView? = null
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var ivLogo: ImageView? = null

    private var ivWordsDecor: ImageView? = null
    private var ivBornDecor: ImageView? = null
    private var ivTitlesDecor: ImageView? = null
    private var ivAliasesDecor: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getString(ARG_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter?.checkDiedDate()
    }

    override fun onDestroyView() {
        hideMessage()
        super.onDestroyView()
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

    private fun initView(view: View) {
        tvWords = view.findViewById(R.id.tv_words)
        tvBorn = view.findViewById(R.id.tv_born)
        tvTitles = view.findViewById(R.id.tv_titles)
        tvAliases = view.findViewById(R.id.tv_aliases)
        btnFather = view.findViewById(R.id.btn_father)
        btnMother = view.findViewById(R.id.btn_mother)
        tvFatherTitle = view.findViewById(R.id.tv_father_title)
        tvMotherTitle = view.findViewById(R.id.tv_mother_title)
        ivBack = view.findViewById(R.id.iv_back)
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar)
        ivWordsDecor = view.findViewById(R.id.iv_words_decor)
        ivBornDecor = view.findViewById(R.id.iv_born_decor)
        ivTitlesDecor = view.findViewById(R.id.iv_titles_decor)
        ivAliasesDecor = view.findViewById(R.id.iv_aliases_decor)
        ivLogo = view.findViewById(R.id.iv_logo)

        btnFather?.setOnClickListener { _ -> presenter?.onFatherBtnClick() }
        btnMother?.setOnClickListener { _ -> presenter?.onMotherBtnClick() }
        ivBack?.setOnClickListener { _ -> activity?.onBackPressed() }
    }

    override fun setColor(primaryColorRes: Int, accentColorRes: Int) {
        val primaryColor = ContextCompat.getColor(activity!!, primaryColorRes)
        val accentColor = ContextCompat.getColor(activity!!, accentColorRes)
        ivWordsDecor?.setColorFilter(accentColor)
        ivBornDecor?.setColorFilter(accentColor)
        ivTitlesDecor?.setColorFilter(accentColor)
        ivAliasesDecor?.setColorFilter(accentColor)

        btnFather?.setBackgroundColor(primaryColor)
        btnMother?.setBackgroundColor(primaryColor)

        collapsingToolbar?.setContentScrimColor(primaryColor)
    }

    override fun setWords(words: String) {
        tvWords?.text = words
    }

    override fun setBorn(born: String) {
        tvBorn?.text = born
    }

    override fun setTitles(titles: String) {
        tvTitles?.text = titles
    }

    override fun setAliases(aliases: String) {
        tvAliases?.text = aliases
    }

    override fun showFatherButton(father: String) {
        btnFather?.visibility = View.VISIBLE
        tvFatherTitle?.visibility = View.VISIBLE
        btnFather?.text = father
    }

    override fun showMotherButton(mother: String) {
        btnMother?.visibility = View.VISIBLE
        tvMotherTitle?.visibility = View.VISIBLE
        btnMother?.text = mother
    }

    override fun showDiedMessage(date: String) {
        showMessage(message = date, indefinite = true)
    }

    override fun showNextCharacterScreen(id: String) {
        getRouter()?.navigateToCharacterFragment(id)
    }

    override fun getCharacterId(): String? = characterId

    override fun setName(name: String) {
        collapsingToolbar?.title = name
    }

    override fun setImage(id: Int) {
        ivLogo?.setImageResource(id)
    }
}
