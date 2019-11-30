package ru.skillbranch.gameofthrones.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.presentation.character.CharacterFragment


class MainActivity : AppCompatActivity(), IRouter {

    companion object {
        @JvmStatic
        fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var toolbar: Toolbar? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.title = getString(ru.skillbranch.gameofthrones.R.string.app_name)
        setSupportActionBar(toolbar)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = PagerAdapter(supportFragmentManager, this@MainActivity)
        val tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val actionSearch = menu.findItem(R.id.menu_search)
        searchView = actionSearch.actionView as SearchView
        searchView?.inputType = InputType.TYPE_TEXT_VARIATION_URI
        searchView?.queryHint = getString(ru.skillbranch.gameofthrones.R.string.menu_search_hint)

//        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null && !query.isEmpty()) {
//                    loadUrl(getString(ru.skillbranch.gameofthrones.R.string.shema) + query)
//                    clearSearchView()
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun navigateToCharacterFragment(id: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.cl_container, CharacterFragment.newInstance(id), id)
            .addToBackStack(id)
            .commit()
    }
}
