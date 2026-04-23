package com.example.appthitracnghiem.ui.home.history.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryExamBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment

@Suppress("DEPRECATION")
class FragmentHistoryExam : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentHistoryExamBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.answerHistory1.setBackgroundResource(R.drawable.bg_answer_fail)
        binding.answerHistory3.setBackgroundResource(R.drawable.select_text_view)

        click()
    }

    private fun click() {
        binding.backHistoryExam.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}