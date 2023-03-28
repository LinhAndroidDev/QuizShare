package com.example.appthitracnghiem.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.category.FragmentCategory
import com.example.appthitracnghiem.ui.home.createtest.FragmentCreateTest
import com.example.appthitracnghiem.ui.home.historytest.FragmentHistory
import com.example.appthitracnghiem.ui.home.home.FragmentHome
import com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("DEPRECATION")
class HomeActivity : BaseActivity<EmptyViewModel>() {
    private var backPressTime: Long = 0

    @SuppressLint("ClickableViewAccessibility", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        if (CheckConnect.haveNetworkConnected(this@HomeActivity)) {
            resetTab()
            functionHome.isSelected = true
            replaceFragment(FragmentHome())

            bottomWrap?.setOnTouchListener { _, _ -> true }

            click()
        } else {
            CheckConnect.showToastShort(this@HomeActivity, "Bạn đang ngoại tuyến")
        }
    }

    private fun click() {
        iconSearch.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCategory) {
                resetTab()
                replaceFragment(FragmentCategory())
            }
        }

        functionHome.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHome) {
                resetTab()
                functionHome.isSelected = true
                replaceFragment(FragmentHome())
            }
        }

        functionCreate.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCreateTest) {
                resetTab()
                functionCreate.isSelected = true
                replaceFragment(FragmentCreateTest())
            }
        }

        functionLeaderboard.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHistory) {
                resetTab()
                functionLeaderboard.isSelected = true
                replaceFragment(FragmentHistory())
            }
        }

        functionProfile.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentProfile) {
                resetTab()
                functionProfile.isSelected = true
                replaceFragment(FragmentProfile())
            }
        }
    }

    /** Select Icon */
    private fun setSelectIcon() {
        val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)

        if (fm is FragmentHome) {
            resetTab()
            functionHome.isSelected = true
        }
        if (fm is FragmentCategory) {
            resetTab()
        }
        if (fm is FragmentCreateTest) {
            resetTab()
            functionCreate.isSelected = true
        }
        if (fm is FragmentHistory) {
            resetTab()
            functionLeaderboard.isSelected = true
        }
        if (fm is FragmentProfile) {
            resetTab()
            functionProfile.isSelected = true
        }
    }

    /** Reset Select Icon */
    private fun resetTab() {
        functionHome.isSelected = false
        functionCreate.isSelected = false
        functionLeaderboard.isSelected = false
        functionProfile.isSelected = false
    }

    /** Replace Fragment */
    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager.beginTransaction()
        fm.setCustomAnimations(
            R.anim.animation_enter_right, R.anim.animation_exit_left,
            R.anim.animation_enter_left, R.anim.animation_exit_right
        )
        fm.replace(R.id.changeIdHome, fragment).addToBackStack(null).commit()
    }

    /** Click Back */
    override fun onBackPressed() {
        val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)

        if (fm != null && fm is BaseFragment<*>) {
            if (fm.onFragmentBack()) {
                if (backPressTime + 2000 > System.currentTimeMillis()) {
                    finish()
                } else {
                    Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show()
                }
                backPressTime = System.currentTimeMillis()
            } else {
                super.onBackPressed()
                setSelectIcon()
            }
        } else {
            super.onBackPressed()
            setSelectIcon()
        }
    }
}