package com.example.appthitracnghiem.ui.home.profile.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment

class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        replaceFragment(FragmentSetting())
    }

    /** Replace Fragment*/
    private fun replaceFragment(fm : Fragment){
        val fragment = supportFragmentManager.beginTransaction()
        fragment.addToBackStack(null)
        fragment.replace(R.id.replaceFragmentSetting,fm).commit()
    }

    /** Click back*/
    override fun onBackPressed() {
        val fm = supportFragmentManager.findFragmentById(R.id.replaceFragmentSetting)
        if(fm != null && fm is BaseFragment){
            if(fm.onFragmentBack()){
                finish()
            } else{
                super.onBackPressed()
            }
        } else{
            super.onBackPressed()
        }
    }
}