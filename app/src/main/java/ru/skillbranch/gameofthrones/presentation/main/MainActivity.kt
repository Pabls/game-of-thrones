package ru.skillbranch.gameofthrones.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import ru.skillbranch.gameofthrones.utils.AppConfig
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.base.BaseActivity
import ru.skillbranch.gameofthrones.presentation.base.BasePresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import java.lang.Exception


class MainActivity : BaseActivity(), IRouter, IMainView {

    companion object {
        @JvmStatic
        fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var presenter: MainPresenter? = null
    private var toolbar: Toolbar? = null
    private var searchView: SearchView? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val actionSearch = menu.findItem(R.id.menu_search)
        searchView = actionSearch.actionView as SearchView
        searchView?.inputType = InputType.TYPE_CLASS_TEXT
        searchView?.queryHint = getString(ru.skillbranch.gameofthrones.R.string.menu_search_hint)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                setSearchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                setSearchQuery(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun navigateToCharacterFragment(id: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.cl_container, CharacterFragment.newInstance(id), id)
            .addToBackStack(id)
            .commit()
    }

    override fun inject() {
        getComponent().inject(this)
    }

    override fun setPresenter(presenter: BasePresenter<IBaseView>) {
        this.presenter = presenter as MainPresenter
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getPresenter(): IPresenter<IBaseView>? {
        return try {
            return if (presenter is BasePresenter<*>)
                presenter as BasePresenter<IBaseView>
            else null
        } catch (ex: Exception) {
            null
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    fun changeCurrentColor(index: Int) {
        val house = getHouseBySelectedTab(index)
        if (house != null) {
            val colors = AppConfig.getColorsByHome(house!!)
            toolbar?.setBackgroundColor(getColor(colors.second))
            tabLayout?.setBackgroundColor(getColor(colors.second))
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(getString(R.string.app_name))
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.viewpager)
        viewPager?.adapter = PagerAdapter(supportFragmentManager, this@MainActivity)
        tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout?.setupWithViewPager(viewPager)

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeCurrentColor(position)
            }

        })
    }

    private fun setSearchQuery(query: String?) {
        try {
            val house = getHouseBySelectedTab()
            val fragment = supportFragmentManager.fragments.find {
                it is CharactersFragment && it.house == house
            }
            if (fragment != null && fragment is CharactersFragment) {
                fragment.setSearchQuery(query)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun getHouseBySelectedTab(index: Int? = null): String? {
        val tab =
            if (index == null) tabLayout?.getTabAt(tabLayout!!.selectedTabPosition) else tabLayout?.getTabAt(
                index
            )
        return tab?.text.toString()
    }
}
