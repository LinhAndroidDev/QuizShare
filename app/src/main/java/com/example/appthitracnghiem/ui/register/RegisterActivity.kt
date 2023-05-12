package com.example.appthitracnghiem.ui.register

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment

@Suppress("DEPRECATION")
class RegisterActivity : BaseActivity<EmptyViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setStatusBar()

        if (CheckConnect.haveNetworkConnected(this@RegisterActivity)) {
            replaceFragmentRegister(FragmentRegister())
        } else {
            CheckConnect.showToastShort(this@RegisterActivity, "Bạn đang ngoại tuyến")
        }
    }

    private fun setStatusBar() {
        val window: Window = this@RegisterActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@RegisterActivity, R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun replaceFragmentRegister(fragment: Fragment) {
        val fm: FragmentTransaction = supportFragmentManager.beginTransaction()
        fm.addToBackStack("Fragment_Register")
        fm.replace(R.id.registerContainerID, fragment).commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.registerContainerID)

        if (fragment != null && fragment is BaseFragment<*>) {
            if (fragment.onFragmentBack()) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}