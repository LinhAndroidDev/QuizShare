package com.example.appthitracnghiem.ui.login_need_refactor

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment

class RegisterActivity : BaseActivity<EmptyViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (CheckConnect.haveNetworkConnected(this@RegisterActivity)) {
            replaceFragmentRegister(FragmentRegister())
        } else {
            CheckConnect.showToastShort(this@RegisterActivity, "Bạn đang ngoại tuyến")
        }
    }

    fun replaceFragmentRegister(fragment: Fragment) {
        var fm: FragmentTransaction = supportFragmentManager.beginTransaction()
        fm.addToBackStack("Fragment_Register")
        fm.replace(R.id.registerContainerID, fragment).commit()
    }

    override fun onBackPressed() {
        var fragment = supportFragmentManager.findFragmentById(R.id.registerContainerID)

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