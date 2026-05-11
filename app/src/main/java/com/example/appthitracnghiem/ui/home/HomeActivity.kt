package com.example.appthitracnghiem.ui.home

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.databinding.ActivityHomePageBinding
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.category.FragmentCategory
import com.example.appthitracnghiem.ui.home.createtest.FragmentCreateTest
import com.example.appthitracnghiem.ui.home.history.FragmentHistory
import com.example.appthitracnghiem.ui.home.home.FragmentHome
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile
import dagger.hilt.android.AndroidEntryPoint
@Suppress("DEPRECATION", "DEPRECATED_IDENTITY_EQUALS")
@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel>() {
    private lateinit var binding: ActivityHomePageBinding
    private var backPressTime: Long = 0

    @SuppressLint("ClickableViewAccessibility", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (CheckConnect.haveNetworkConnected(this@HomeActivity)) {
            resetTab()
            binding.functionHome.isSelected = true
            attachFragment(R.id.changeIdHome, FragmentHome())

            binding.bottomBar.setOnTouchListener { _, _ -> true }

            initUi()
        } else {
            CheckConnect.showToastShort(this@HomeActivity, "Bạn đang ngoại tuyến")
        }
    }

    private fun initUi() {

        binding.iconSearch.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCategory) {
                resetTab()
                attachFragment(R.id.changeIdHome, FragmentCategory())
            }else{
                fm.scrollTop()
            }
        }

        binding.functionHome.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHome) {
                resetTab()
                binding.functionHome.isSelected = true
                val fragmentHome = FragmentHome()
                val fg = supportFragmentManager.beginTransaction()
                fg.replace(R.id.changeIdHome, fragmentHome).addToBackStack(null).commit()
            }else{
                fm.scrollTop()
            }
        }

        binding.functionCreate.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentCreateTest) {
                resetTab()
                binding.functionCreate.isSelected = true
                attachFragment(R.id.changeIdHome, FragmentCreateTest())
            }else{
                fm.scrollTop()
            }
        }

        binding.functionLeaderboard.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentHistory) {
                resetTab()
                binding.functionLeaderboard.isSelected = true
                attachFragment(R.id.changeIdHome, FragmentHistory())
            }
        }

        binding.functionProfile.setOnClickListener {
            val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)
            if (fm !is FragmentProfile) {
                resetTab()
                binding.functionProfile.isSelected = true
                attachFragment(R.id.changeIdHome, FragmentProfile())
            }else{
                fm.scrollTop()
            }
        }

        binding.layoutHomeActivity.viewTreeObserver
            .addOnGlobalLayoutListener {
                val r = Rect()
                binding.layoutHomeActivity.getWindowVisibleDisplayFrame(r)
                val screenHeight: Int = binding.layoutHomeActivity.rootView.height

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                val keypadHeight: Int = screenHeight - r.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    binding.bottomWrap.visibility = View.GONE
                }else{
                    binding.bottomWrap.visibility = View.VISIBLE
                }
            }
    }

    /** Select Icon */
    private fun setSelectIcon() {
        val fm = supportFragmentManager.findFragmentById(R.id.changeIdHome)

        if (fm is FragmentHome) {
            resetTab()
            binding.functionHome.isSelected = true
        }
        if (fm is FragmentCategory) {
            resetTab()
        }
        if (fm is FragmentCreateTest) {
            resetTab()
            binding.functionCreate.isSelected = true
        }
        if (fm is FragmentHistory) {
            resetTab()
            binding.functionLeaderboard.isSelected = true
        }
        if (fm is FragmentProfile) {
            resetTab()
            binding.functionProfile.isSelected = true
        }
    }

    /** Reset Select Icon */
    private fun resetTab() {
        binding.functionHome.isSelected = false
        binding.functionCreate.isSelected = false
        binding.functionLeaderboard.isSelected = false
        binding.functionProfile.isSelected = false
    }

//    @SuppressLint("ClickableViewAccessibility")
//    internal fun hideTabBar(bottomWrapView: NestedScrollView?){
//        bottomWrapView?.setOnTouchListener(TranslateAnimation(this, bottomWrap))
//    }

    internal fun loadingVisible(isLoading: Boolean){
        if(isLoading){
            binding.loadingHome.root.visibility = View.VISIBLE
        }else{
            binding.loadingHome.root.visibility = View.GONE
        }
    }

    internal fun clickAvatar(){
        binding.functionHome.isSelected = false
        binding.functionProfile.isSelected = true
    }

//    internal fun visibleTaBar(blur: Double){
//        bottomWrap.alpha =  blur.toFloat()
//    }

    private fun attachFragment(
        fragmentHolderLayoutId: Int,
        fragment: Fragment
        )
    {
        val manager: FragmentManager = supportFragmentManager
        val fg: FragmentTransaction = manager.beginTransaction()
            fg.add(fragmentHolderLayoutId, fragment).addToBackStack(null).commit()
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
            } else if (fm !is FragmentHome) {
                if(fm is FragmentCreateTest || fm is FragmentHistory || fm is FragmentProfile || fm is FragmentCategory){
                    val fragmentHome = FragmentHome()
                    val fragment: FragmentTransaction = supportFragmentManager.beginTransaction()
                    fragment.replace(R.id.changeIdHome, fragmentHome)
                        .addToBackStack(null)
                        .commit()
                    resetTab()
                    binding.functionHome.isSelected = true
                }else{
                    super.onBackPressed()
                    setSelectIcon()
                }
            }
        } else {
            super.onBackPressed()
            setSelectIcon()
        }
    }
}