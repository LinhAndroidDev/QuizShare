package com.example.appthitracnghiem.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.intro.IntroActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animLogo: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_logo)
        logo.startAnimation(animLogo)

        flashScreen()
    }

    private fun flashScreen() {
        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (CheckConnect.haveNetworkConnected(this@MainActivity)) {
                    val intent = if (viewModel.isFirstInstallApp()) {
                        viewModel.clearFirstInstallApp()
                        Intent(this@MainActivity, IntroActivity::class.java)
                    } else {
                        if (viewModel.isUserLoggedIn()) {
                            Intent(this@MainActivity, HomeActivity::class.java)
                        } else {
                            Intent(this@MainActivity, LoginActivity::class.java)
                        }
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