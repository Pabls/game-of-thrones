package ru.skillbranch.gameofthrones.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment
import java.lang.Exception


class MainActivity : AppCompatActivity(), IRouter {

    companion object {
        @JvmStatic
        fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var toolbar: Toolbar? = null
    private var searchView: SearchView? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(getString(R.string.app_name))

        viewPager = findViewById(R.id.viewpager)
        viewPager?.adapter = PagerAdapter(supportFragmentManager, this@MainActivity)
        tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout?.setupWithViewPager(viewPager)
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

    private fun setSearchQuery(query: String?) {
        try {
            val tab = tabLayout?.getTabAt(tabLayout!!.selectedTabPosition)
            val house = tab?.text
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
}
