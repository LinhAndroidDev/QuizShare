package com.example.appthitracnghiem.ui.home.profile.setting

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment

@Suppress("DEPRECATION")
class SettingActivity : BaseActivity<EmptyViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setStatusBar()

        replaceFragment(FragmentSetting())
    }

    private fun setStatusBar() {
        val window: Window = this@SettingActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@SettingActivity, R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        };//  set status text dark
    }

    /** Replace Fragment*/
    private fun replaceFragment(fm: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.addToBackStack(null)
        fragment.replace(R.id.replaceFragmentSetting, fm).commit()
    }

    /** Click back*/
    override fun onBackPressed() {
        val fm = supportFragmentManager.findFragmentById(R.id.replaceFragmentSetting)
        if (fm != null && fm is BaseFragment<*>) {
            if (fm.onFragmentBack()) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}