package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.ActivityIntroLoginBinding
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.ui.register.RegisterActivity

@Suppress("DEPRECATION")
class IntroLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.anim_translate_enter_right, R.anim.anim_translate_exit_left)

        initUi()
    }

    private fun setStatusBar() {
        val window: Window = this@IntroLoginActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@IntroLoginActivity, R.color.backgroundIntro)
    }

    private fun initUi() {

        setStatusBar()

        binding.loginIntro.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerIntro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}