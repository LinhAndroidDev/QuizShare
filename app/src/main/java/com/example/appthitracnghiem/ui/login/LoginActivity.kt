package com.example.appthitracnghiem.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.PreferenceUtil

class LoginActivity : BaseActivity() {
    var CHECK_SHOW_TUTORIAL: String = "CHECK_SHOW_TUTORIAL"
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        val checkShowTutorial: CheckShowTutorial = CheckShowTutorial(this@LoginActivity)
//        checkShowTutorial.putBooleanValue("CHECK_SHOW_TUTORIAL", false)

        mPreferenceUtil.defaultPref().edit().putBoolean(CHECK_SHOW_TUTORIAL, false).apply()

        val checkShowTutorrial: PreferenceUtil = PreferenceUtil(this)

        if (CheckConnect.haveNetworkConnected(this@LoginActivity)) {
            replaceFragmentLogin(Fragment_Login())
        } else {
            CheckConnect.showToastShort(this@LoginActivity, "Bạn đang ngoại tuyến")
        }
    }

    companion object

    fun replaceFragmentLogin(fragment: Fragment) {
        val fm: FragmentTransaction = supportFragmentManager.beginTransaction()
        fm.addToBackStack("Fragment_Login")
        fm.replace(R.id.loginContainerID, fragment).commit()
    }

    override fun onBackPressed() {

        val fragment = supportFragmentManager.findFragmentById(R.id.loginContainerID)
        if (fragment != null && fragment is BaseFragment) {
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