package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_intro_login.*

class IntroLoginActivity : AppCompatActivity() {
    private var backPressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_login)

        overridePendingTransition(R.anim.anim_translate_enter_right, R.anim.anim_translate_exit_left)

        click()
    }

    private fun click() {
        loginIntro.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerIntro.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        toDoLate.setOnClickListener {
            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show()
        }
        backPressTime = System.currentTimeMillis()
    }
}