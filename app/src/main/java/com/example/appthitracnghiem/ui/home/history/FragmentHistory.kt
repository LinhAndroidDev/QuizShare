package com.example.appthitracnghiem.ui.home.history

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.FragmentHistoryQuestion
import com.example.appthitracnghiem.ui.home.history.test.FragmentHistoryTest
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHistory.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHistory : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()

        setText()
    }

    @SuppressLint("CommitTransaction")
    private fun click() {
        historyTest.setOnClickListener{
            val fragmentHistoryTest: FragmentHistoryTest = FragmentHistoryTest()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryTest).addToBackStack(null).commit()
        }

        historyQuiz.setOnClickListener {
            val fragmentHistoryQuestion: FragmentHistoryQuestion = FragmentHistoryQuestion()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryQuestion).addToBackStack(null).commit()
        }
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtLichSu.typeface = semibold
        txtLichSuThi.typeface = semibold
        txtDaLuu.typeface = semibold
        txtLichSuCauHoi.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}