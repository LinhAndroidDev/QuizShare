package com.example.appthitracnghiem.ui.home.history.test.topic

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryTopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_topic)

        // Stack: [topic replace] = 1 entry, [+ answer add] = 2 entries. Pop removes answer overlay only.
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fm = supportFragmentManager
                    if (fm.isStateSaved) return
                    if (fm.backStackEntryCount > 1) {
                        fm.popBackStack()
                    } else {
                        finish()
                    }
                }
            },
        )

        if (savedInstanceState == null) {
            replaceFragment(FragmentHistoryTopic())
        }
    }

    private fun replaceFragment(fm: Fragment) {
        val fragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.changeIdTopicHistory, fm).addToBackStack(null).commit()
    }
}