package com.example.appthitracnghiem.ui.home.home.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.home.user.FragmentFromUser
import com.example.appthitracnghiem.ui.home.home.system.FragmentSystem

enum class Page(@StringRes val titleRes: Int, val fragment: Class<out Fragment>) {
    FragmentFirst(R.string.tab_department_system, FragmentSystem::class.java),
    FragmentSecond(R.string.tab_department_user, FragmentFromUser::class.java),
}

@Suppress("DEPRECATION")
class ViewPagerDepartment(
    private val context: Context,
    fm: FragmentManager,
    behavior: Int,
) : FragmentStatePagerAdapter(fm, behavior) {

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
        return context.getString(pages[position].titleRes)
    }
}
