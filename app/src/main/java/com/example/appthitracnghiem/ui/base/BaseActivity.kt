package com.example.appthitracnghiem.ui.base

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.utils.PreferenceUtil
import java.lang.reflect.ParameterizedType

@Suppress("DEPRECATION")
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this)[(this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>]
        viewModel.mPreferenceUtil = PreferenceUtil(this)
        overridePendingTransition(
            R.anim.anim_translate_enter_right,
            R.anim.anim_translate_exit_left
        )
    }

    open fun bindData(){
        viewModel.errorApiLiveData.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.anim_translate_enter_left,
            R.anim.anim_translate_exit_right
        )
    }
}