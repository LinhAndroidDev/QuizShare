package com.example.appthitracnghiem.ui.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.utils.PreferenceUtil

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mPreferenceUtil: PreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferenceUtil = PreferenceUtil(this)

        overridePendingTransition(R.anim.anim_translate_enter_right, R.anim.anim_translate_exit_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_translate_enter_left, R.anim.anim_translate_exit_right)
    }
}