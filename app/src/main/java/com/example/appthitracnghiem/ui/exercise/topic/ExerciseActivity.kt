package com.example.appthitracnghiem.ui.exercise.topic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val examId = intent.getIntExtra(ExamSessionExtras.INTENT_EXAM_ID, 0)
        val timeMinutes = intent.getIntExtra(ExamSessionExtras.INTENT_TIME_MINUTES, 0)
        val topicUiMode = intent.getIntExtra(ExamSessionExtras.INTENT_TOPIC_UI_MODE, -1)
        val topicUserName = intent.getStringExtra(ExamSessionExtras.INTENT_TOPIC_USER_NAME).orEmpty()
        val topicUserAvatar = intent.getStringExtra(ExamSessionExtras.INTENT_TOPIC_USER_AVATAR).orEmpty()
        val topic = FragmentTopic().apply {
            arguments = Bundle().apply {
                putInt(ExamSessionExtras.ARG_EXAM_ID, examId)
                putInt(ExamSessionExtras.ARG_TIME_MINUTES, timeMinutes)
                putInt(ExamSessionExtras.ARG_TOPIC_UI_MODE, topicUiMode)
                putString(ExamSessionExtras.ARG_TOPIC_USER_NAME, topicUserName)
                putString(ExamSessionExtras.ARG_TOPIC_USER_AVATAR, topicUserAvatar)
            }
        }
        replaceFragment(topic)
    }

    private fun replaceFragment(fm: Fragment){
        val fragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.changeIdExercise,fm).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.changeIdExercise)
        if (fragment != null && fragment is BaseFragment<*>) {
            if (fragment.onFragmentBack()) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}