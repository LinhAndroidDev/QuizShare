package com.example.appthitracnghiem.ui.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.FragmentExercise
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTopic.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTopic : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()
    }

    private fun click() {
        backTopic.setOnClickListener {
            activity?.onBackPressed()
        }

        doTestNow.setOnClickListener {
            val fragmentExercise: FragmentExercise = FragmentExercise()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.replace(R.id.changeIdExercise,fragmentExercise).addToBackStack(null).commit()
        }
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }
}