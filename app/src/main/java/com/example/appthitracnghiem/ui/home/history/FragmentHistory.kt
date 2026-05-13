package com.example.appthitracnghiem.ui.home.history

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.FragmentHistoryQuestion
import com.example.appthitracnghiem.ui.home.history.saved.department.FragmentHistoryDepartmentSaved
import com.example.appthitracnghiem.ui.home.history.test.general.FragmentHistoryTest
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistory : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

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
        } //  set status text dark
    }

    @SuppressLint("CommitTransaction")
    private fun initUi() {

        setStatusBar()

        binding.historyTest.setOnClickListener {
            val fragmentHistoryTest = FragmentHistoryTest()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentHistoryTest).addToBackStack(null).commit()
        }

        binding.historyQuiz.setOnClickListener {
            val fragmentHistoryQuestion = FragmentHistoryQuestion()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentHistoryQuestion).addToBackStack(null).commit()
        }

        binding.saved.setOnClickListener {
            val fragmentHistorySaved = FragmentHistoryDepartmentSaved()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentHistorySaved).addToBackStack(null).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}