package com.example.appthitracnghiem.ui.intro.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.ui.intro.FragmentIntroFirst
import com.example.appthitracnghiem.ui.intro.FragmentIntroLogin
import com.example.appthitracnghiem.ui.intro.FragmentIntroSecond
import com.example.appthitracnghiem.ui.intro.FragmentIntroThird

enum class Page(val fragment: Class<out Fragment>) {
    FragmentFirst(FragmentIntroFirst::class.java),
    FragmentSecond(FragmentIntroSecond::class.java),
    FragmentThird(FragmentIntroThird::class.java)
}

@Suppress("DEPRECATION")
class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    private var page: MutableList<Page> = mutableListOf<Page>().apply {
        addAll(Page.values())
    }

    override fun getItem(position: Int): Fragment {
        return page[position].fragment.newInstance()
    }

    override fun getCount(): Int {
        return page.size
    }

}