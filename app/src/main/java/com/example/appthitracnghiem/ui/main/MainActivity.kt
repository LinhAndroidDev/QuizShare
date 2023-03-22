package com.example.appthitracnghiem.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.data.CheckShowTutorial
import com.example.appthitracnghiem.data.MySharedPreferences
import com.example.appthitracnghiem.ui.home.home.HomeActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var KEY_FIRST_INSTALL : String = "KEY_FIRST_INSTALL"
    var CHECK_SHOW_TUTORIAL : String = "CHECK_SHOW_TUTORIAL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mySharedPreferences : MySharedPreferences = MySharedPreferences(this@MainActivity)
        val checkShowTutorial : CheckShowTutorial = CheckShowTutorial(this@MainActivity)

        val anim_logo : Animation = AnimationUtils.loadAnimation(this@MainActivity,R.anim.anim_logo)
        logo.startAnimation(anim_logo)

        val countDownTimer : CountDownTimer = object : CountDownTimer(3000,3000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if(CheckConnect.haveNetworkConnected(this@MainActivity)){
                    if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                        if(checkShowTutorial.getBooleanValue(CHECK_SHOW_TUTORIAL)){
                            val intent : Intent = Intent(this@MainActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            val intent : Intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true)
                    }
                }else{
                    CheckConnect.showToastShort(this@MainActivity,"Bạn đang ngoại tuyến")
                    finish()
                }
                }
        }
        countDownTimer.start()
    }

    override fun onBackPressed() {

    }
}