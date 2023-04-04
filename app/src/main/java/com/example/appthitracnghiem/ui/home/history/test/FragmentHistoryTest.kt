package com.example.appthitracnghiem.ui.home.history.test

import android.content.res.Resources
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
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter
import com.example.appthitracnghiem.ui.home.history.test.adapter.HistoryTestAdapter
import kotlinx.android.synthetic.main.fragment_history_test.*
import kotlinx.android.synthetic.main.fragment_list_test.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHistoryTest.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentHistoryTest : BaseFragment<EmptyViewModel>() {
    lateinit var listTest: MutableList<Test>
    lateinit var testAdapter: HistoryTestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listTest = mutableListOf()
        listTest.add(Test(1,R.drawable.icon_test_subject,"Đề 1","Toán 12 Trắc nghiệm"))
        listTest.add(Test(2,R.drawable.icon_test_subject,"Đề 2","Toán 12 Trắc nghiệm"))
        listTest.add(Test(3,R.drawable.icon_test_subject,"Đề 3","Toán 12 Trắc nghiệm"))
        listTest.add(Test(4,R.drawable.icon_test_subject,"Đề 4","Toán 12 Trắc nghiệm"))

        testAdapter = HistoryTestAdapter(requireActivity(),listTest)

        val linear1: LinearLayoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        val linear2: LinearLayoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)

        listTestFromSystem.layoutManager = linear1
        listTestFromSystem.adapter = testAdapter

        listTestFromUser.layoutManager = linear2
        listTestFromUser.adapter = testAdapter

        click()

        setText()
    }

    private fun click() {
        backHistoryTest.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
        txtTitleHistory.typeface = semibold
        txtFromSystem.typeface = semibold
        txtFromUser.typeface = semibold
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_test, container, false)
    }
}