package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.home.HomeActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.ui.login.RegisterActivity
import kotlinx.android.synthetic.main.fragment_intro_login.*

class IntroLoginActivity : AppCompatActivity() {
    private var backPressTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_login)

        loginIntro.setOnClickListener {
            var intent : Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerIntro.setOnClickListener {
            var intent : Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        toDoLate.setOnClickListener {
            var intent : Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(this,"Nhấn lần nữa để thoát",Toast.LENGTH_SHORT).show()
        }
        backPressTime = System.currentTimeMillis()
    }
}