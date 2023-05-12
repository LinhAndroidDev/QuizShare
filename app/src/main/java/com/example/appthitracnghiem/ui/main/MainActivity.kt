package com.example.appthitracnghiem.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.intro.IntroActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBar()

        val animLogo: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_logo)
        logo.startAnimation(animLogo)

        flashScreen()
    }

    private fun setStatusBar() {
        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backgroundIntro)
    }

    private fun flashScreen() {
        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (CheckConnect.haveNetworkConnected(this@MainActivity)) {
                    val intent = if (viewModel.isFirstInstallApp()) {
                        if (viewModel.isUserLoggedIn()) {
                            Intent(this@MainActivity, HomeActivity::class.java)
                        } else {
                            Intent(this@MainActivity, LoginActivity::class.java)
                        }
                    } else {
                        viewModel.clearFirstInstallApp()
                        Intent(this@MainActivity, IntroActivity::class.java)
                    }
                    startActivity(intent)
                } else {
                    CheckConnect.showToastShort(this@MainActivity, "Bạn đang ngoại tuyến")
                }
                finish()
            }
        }
        countDownTimer.start()
    }

    override fun onBackPressed() {

    }
}