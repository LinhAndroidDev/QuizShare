package com.example.appthitracnghiem.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R

abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.anim_translate_enter_right, R.anim.anim_translate_exit_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_translate_enter_left, R.anim.anim_translate_exit_right)
    }
}