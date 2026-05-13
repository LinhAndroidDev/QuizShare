package com.example.appthitracnghiem.ui.exercise.exercise

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.ActivityExamBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.ui.exercise.exercise.exam.FragmentExam
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Run before FragmentManager's default callback so we don't pop to an empty changeIdExam (white screen).
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fm = supportFragmentManager
                    if (fm.isStateSaved) return
                    val top = fm.findFragmentById(R.id.changeIdExam) as? BaseFragment<*>
                    if (top != null && top.onFragmentBack()) {
                        finish()
                        return
                    }
                    if (fm.backStackEntryCount > 0) {
                        fm.popBackStack()
                    } else {
                        finish()
                    }
                }
            },
        )

        if (savedInstanceState == null) {
            val examId = intent.getIntExtra(ExamSessionExtras.INTENT_EXAM_ID, 0)
            val timeMinutes = intent.getIntExtra(ExamSessionExtras.INTENT_TIME_MINUTES, 0)
            val startTimestamp = intent.getStringExtra(ExamSessionExtras.INTENT_EXAM_START_TIMESTAMP).orEmpty()
            val examFragment = FragmentExam().apply {
                arguments = Bundle().apply {
                    putInt(ExamSessionExtras.ARG_EXAM_ID, examId)
                    putInt(ExamSessionExtras.ARG_TIME_MINUTES, timeMinutes)
                    putString(ExamSessionExtras.ARG_EXAM_START_TIMESTAMP, startTimestamp)
                }
            }
            replaceFragment(examFragment)
        }
    }

    private fun replaceFragment(fm: Fragment) {
        val fragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.changeIdExam, fm).addToBackStack(null).commit()
    }

    internal fun loadingVisible(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingExam.root.visibility = View.VISIBLE
        } else {
            binding.loadingExam.root.visibility = View.GONE
        }
    }
}