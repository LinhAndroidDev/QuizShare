package com.example.appthitracnghiem.ui.home.history.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryQuestionBinding
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.adapter.HistoryQuestionAdapter
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistoryQuestion : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentHistoryQuestionBinding? = null
    private val binding get() = _binding!!
    lateinit var listQuestionHistory: MutableList<Test>
    lateinit var historyQuestionAdapter: HistoryQuestionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listQuestionHistory = arrayListOf()
        val subtitle = getString(R.string.history_question_subtitle_sample)
        for (i in 1..7) {
            listQuestionHistory.add(
                Test(
                    i,
                    R.drawable.icon_test_subject,
                    getString(R.string.history_question_title_sample, i),
                    subtitle,
                ),
            )
        }

        historyQuestionAdapter = HistoryQuestionAdapter(requireActivity(),listQuestionHistory)

        val linear = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        binding.rcvListHistoryQuestion.layoutManager = linear
        binding.rcvListHistoryQuestion.adapter = historyQuestionAdapter

        click()
    }

    private fun click() {
        binding.backHistoryQuestion.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}