package com.example.appthitracnghiem.ui.home.history.question

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryQuestionBinding
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.adapter.HistoryQuestionAdapter

@Suppress("DEPRECATION")
class FragmentHistoryQuestion : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentHistoryQuestionBinding? = null
    private val binding get() = _binding!!
    lateinit var listQuestionHistory: MutableList<Test>
    lateinit var historyQuestionAdapter: HistoryQuestionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listQuestionHistory = arrayListOf()
        listQuestionHistory.add(Test(1,R.drawable.icon_test_subject,"Đề 1","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(2,R.drawable.icon_test_subject,"Đề 2","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(3,R.drawable.icon_test_subject,"Đề 3","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(4,R.drawable.icon_test_subject,"Đề 4","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(5,R.drawable.icon_test_subject,"Đề 5","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(6,R.drawable.icon_test_subject,"Đề 6","Toán • 12 Trắc nghiệm"))
        listQuestionHistory.add(Test(7,R.drawable.icon_test_subject,"Đề 7","Toán • 12 Trắc nghiệm"))

        historyQuestionAdapter = HistoryQuestionAdapter(requireActivity(),listQuestionHistory)

        val linear: LinearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        binding.rcvListHistoryQuestion.layoutManager = linear
        binding.rcvListHistoryQuestion.adapter = historyQuestionAdapter

        setText()

        click()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
        binding.txtHistoryTest.typeface = semibold
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