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


class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var toolbar: Toolbar? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ru.skillbranch.gameofthrones.R.layout.activity_main)
        toolbar = findViewById(ru.skillbranch.gameofthrones.R.id.toolbar)
        toolbar?.title = getString(ru.skillbranch.gameofthrones.R.string.app_name)
        setSupportActionBar(toolbar)

        val viewPager = findViewById<ViewPager>(ru.skillbranch.gameofthrones.R.id.viewpager)
        viewPager.adapter = PagerAdapter(supportFragmentManager, this@MainActivity)
        val tabLayout = findViewById<TabLayout>(ru.skillbranch.gameofthrones.R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(ru.skillbranch.gameofthrones.R.menu.search_menu, menu)
        val actionSearch = menu.findItem(ru.skillbranch.gameofthrones.R.id.menu_search)
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
}
