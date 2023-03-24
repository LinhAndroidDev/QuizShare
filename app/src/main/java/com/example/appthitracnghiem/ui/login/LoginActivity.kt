package com.example.appthitracnghiem.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment

class LoginActivity : BaseActivity<EmptyViewModel>() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (CheckConnect.haveNetworkConnected(this@LoginActivity)) {
            replaceFragmentLogin(FragmentLogin())
        } else {
            CheckConnect.showToastShort(this@LoginActivity, "Bạn đang ngoại tuyến")
        }
    }

    private fun replaceFragmentLogin(fragment: Fragment) {
        val fm: FragmentTransaction = supportFragmentManager.beginTransaction()
        fm.addToBackStack("FragmentLogin")
        fm.replace(R.id.loginContainerID, fragment).commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.loginContainerID)
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