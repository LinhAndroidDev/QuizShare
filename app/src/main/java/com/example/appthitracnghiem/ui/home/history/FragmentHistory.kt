package com.example.appthitracnghiem.ui.home.history

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.FragmentHistoryQuestion
import com.example.appthitracnghiem.ui.home.history.saved.department.FragmentHistoryDepartmentSaved
import com.example.appthitracnghiem.ui.home.history.test.general.FragmentHistoryTest
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_history.*

@Suppress("DEPRECATION")
class FragmentHistory : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        };//  set status text dark
    }

    @SuppressLint("CommitTransaction")
    private fun initUi() {

        setStatusBar()

        historyTest.setOnClickListener{
            val fragmentHistoryTest = FragmentHistoryTest()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryTest).addToBackStack(null).commit()
        }

        historyQuiz.setOnClickListener {
            val fragmentHistoryQuestion = FragmentHistoryQuestion()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryQuestion).addToBackStack(null).commit()
        }

        saved.setOnClickListener {
            val fragmentHistorySaved = FragmentHistoryDepartmentSaved()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistorySaved).addToBackStack(null).commit()
        }

        setText()
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