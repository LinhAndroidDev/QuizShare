package com.example.appthitracnghiem.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.data.MySharedPreferences
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.home.home.HomeActivity
import com.example.appthitracnghiem.ui.intro.IntroActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    var KEY_FIRST_INSTALL: String = "KEY_FIRST_INSTALL"
    var CHECK_SHOW_TUTORIAL: String = "CHECK_SHOW_TUTORIAL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val mySharedPreferences: MySharedPreferences = MySharedPreferences(this@MainActivity)
//        val checkShowTutorial: CheckShowTutorial = CheckShowTutorial(this@MainActivity)

        val animLogo: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_logo)
        logo.startAnimation(animLogo)

        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (CheckConnect.haveNetworkConnected(this@MainActivity)) {
                    if (mPreferenceUtil.defaultPref().getBoolean(KEY_FIRST_INSTALL, true)) {
//                        mPreferenceUtil.defaultPref().edit().putBoolean(KEY_FIRST_INSTALL, true)
//                            .apply()
//                        val intent: Intent = Intent(this@MainActivity, IntroActivity::class.java)
//                        startActivity(intent)
//                        finish()
                        if (mPreferenceUtil.defaultPref().getBoolean(CHECK_SHOW_TUTORIAL, true)) {
                            val intent: Intent = Intent(this@MainActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent: Intent =
                                Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
//                        if (mPreferenceUtil.defaultPref().getBoolean(CHECK_SHOW_TUTORIAL, true)) {
//                            val intent: Intent = Intent(this@MainActivity, HomeActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            val intent: Intent =
//                                Intent(this@MainActivity, LoginActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
                        mPreferenceUtil.defaultPref().edit().putBoolean(KEY_FIRST_INSTALL, true)
                            .apply()
                        val intent: Intent = Intent(this@MainActivity, IntroActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    CheckConnect.showToastShort(this@MainActivity, "Bạn đang ngoại tuyến")
                    finish()
                }
            }
        }
        countDownTimer.start()
    }

    override fun onBackPressed() {

    }
}