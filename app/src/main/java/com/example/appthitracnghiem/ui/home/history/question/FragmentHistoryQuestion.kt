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
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.question.adapter.HistoryQuestionAdapter
import kotlinx.android.synthetic.main.fragment_history_question.*

@Suppress("DEPRECATION")
class FragmentHistoryQuestion : BaseFragment<EmptyViewModel>() {
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
        rcvListHistoryQuestion.layoutManager = linear
        rcvListHistoryQuestion.adapter = historyQuestionAdapter

        setText()

        click()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
        txtHistoryTest.typeface = semibold
    }

    private fun click() {
        backHistoryQuestion.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_question, container, false)
    }
}