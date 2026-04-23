package com.example.appthitracnghiem.ui.exercise.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.ActivityExamBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.exam.FragmentExam

@Suppress("DEPRECATION")
class ExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(FragmentExam())
    }

    private fun replaceFragment(fm: Fragment){
        val fragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.changeIdExam,fm).addToBackStack(null).commit()
    }

    internal fun loadingVisible(isLoading: Boolean){
        if(isLoading){
            binding.loadingExam.root.visibility = View.VISIBLE
        }else{
            binding.loadingExam.root.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.changeIdExam)
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