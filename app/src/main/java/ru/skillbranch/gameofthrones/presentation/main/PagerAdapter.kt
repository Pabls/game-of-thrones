package ru.skillbranch.gameofthrones.presentation.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.skillbranch.gameofthrones.utils.AppConfig
import ru.skillbranch.gameofthrones.presentation.characters.CharactersFragment

class PagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    private val PAGE_COUNT = 7

    override fun getCount(): Int = PAGE_COUNT

    override fun getItem(position: Int): Fragment = CharactersFragment.newInstance(AppConfig.HOUSES_NAMES[position])


    override fun getPageTitle(position: Int): CharSequence? = AppConfig.HOUSES_NAMES[position]
}