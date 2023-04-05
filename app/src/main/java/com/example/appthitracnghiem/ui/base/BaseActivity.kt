package com.example.appthitracnghiem.ui.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.utils.PreferenceUtil
import java.lang.reflect.ParameterizedType

@Suppress("DEPRECATION")
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewModel =
            ViewModelProviders.of(this)[(this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>]
        viewModel.mPreferenceUtil = PreferenceUtil(this)
        overridePendingTransition(
            R.anim.anim_translate_enter_right,
            R.anim.anim_translate_exit_left
        )
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_translate_enter_left, R.anim.anim_translate_exit_right)
    }
}