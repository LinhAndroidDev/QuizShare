package com.example.appthitracnghiem.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.category.FragmentCategory
import com.example.appthitracnghiem.ui.home.createtest.FragmentCreateTest
import com.example.appthitracnghiem.ui.home.history.FragmentHistory
import com.example.appthitracnghiem.ui.home.home.FragmentHome
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile
import kotlinx.android.synthetic.main.activity_home_page.*

@Suppress("DEPRECATION", "DEPRECATED_IDENTITY_EQUALS")
class HomeActivity : BaseActivity<HomeViewModel>() {
    private var backPressTime: Long = 0

    @SuppressLint("ClickableViewAccessibility", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        if (CheckConnect.haveNetworkConnected(this@HomeActivity)) {
            resetTab()
            functionHome.isSelected = true
//            addFragment(FragmentHome())
            attachFragment(R.id.changeIdHome, FragmentHome())

            bottomWrap?.setOnTouchListener { _, _ -> true }

            initUi()
        } else {
            CheckConnect.showToastShort(this@HomeActivity, "Bạn đang ngoại tuyến")
        }
    }

    private fun initUi() {
        iconSearch.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCategory) {
                resetTab()
//                addFragment(FragmentCategory())

                attachFragment(R.id.changeIdHome, FragmentCategory())
            }
        }

        functionHome.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHome) {
                resetTab()
                functionHome.isSelected = true
                val fragmentHome = FragmentHome()
                val fg = supportFragmentManager.beginTransaction()
                fg.replace(R.id.changeIdHome, fragmentHome).addToBackStack(null).commit()
            }
        }

        functionCreate.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCreateTest) {
                resetTab()
                functionCreate.isSelected = true
//                addFragment(FragmentCreateTest())
                attachFragment(R.id.changeIdHome, FragmentCreateTest())
            }
        }

        functionLeaderboard.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHistory) {
                resetTab()
                functionLeaderboard.isSelected = true
//                addFragment(FragmentHistory())
                attachFragment(R.id.changeIdHome, FragmentHistory())
            }
        }

        functionProfile.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentProfile) {
                resetTab()
                functionProfile.isSelected = true
//                addFragment(FragmentProfile())
                attachFragment(R.id.changeIdHome, FragmentProfile())
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
    private fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.changeIdHome, fragment).addToBackStack(null).commit()
    }

//    @SuppressLint("ClickableViewAccessibility")
//    internal fun hideTabBar(bottomWrapView: NestedScrollView?){
//        bottomWrapView?.setOnTouchListener(TranslateAnimation(this, bottomWrap))
//    }

    internal fun loadingVisible(isLoading: Boolean){
        if(isLoading){
            loadingHome.visibility = View.VISIBLE
        }else{
            loadingHome.visibility = View.GONE
        }
    }

    internal fun clickAvatar(){
        functionHome.isSelected = false
        functionProfile.isSelected = true
    }

    private fun attachFragment(
        fragmentHolderLayoutId: Int,
        fragment: Fragment,
        )
    {
        val manager: FragmentManager = supportFragmentManager
        val fg: FragmentTransaction = manager.beginTransaction()
        if(manager.findFragmentByTag(fragment.tag) == null){
            fg.add(fragmentHolderLayoutId, fragment, fragment.tag).addToBackStack(fragment.tag).commit()
        }else{
            fg.show(manager.findFragmentByTag(fragment.tag)!!).commit()
        }
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
                if (fm !is FragmentHome) {
                    super.onBackPressed()
                    setSelectIcon()
//                    supportFragmentManager.popBackStack()
//                    setSelectIcon()
//                    resetTab()
//                    functionHome.isSelected = true
//                    val fragmentHome = FragmentHome()
//                    val fragment = supportFragmentManager.beginTransaction()
//                    fragment.replace(R.id.changeIdHome, fragmentHome).addToBackStack(null).commit()
                }
            }
        } else {
            super.onBackPressed()
            setSelectIcon()
        }
    }

//    fun hideSoftKeyboard() {
//        val inputMethodManager: InputMethodManager =
//            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
//    }
}