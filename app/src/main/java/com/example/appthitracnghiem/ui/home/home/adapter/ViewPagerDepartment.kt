package com.example.appthitracnghiem.ui.home.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.ui.home.home.user.FragmentFromUser
import com.example.appthitracnghiem.ui.home.home.system.FragmentSystem

enum class Page(val title: String, val fragment: Class<out Fragment>) {
    FragmentFirst("Hệ thống", FragmentSystem::class.java),
    FragmentSecond("Người dùng", FragmentFromUser::class.java);
}

@Suppress("DEPRECATION")
class ViewPagerDepartment(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    private val pages: List<Page> = arrayListOf<Page>().apply {
        addAll(Page.values())
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}