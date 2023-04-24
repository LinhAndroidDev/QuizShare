package com.example.appthitracnghiem.ui.home.history.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.home.history.saved.adapter.HistoryDepartmentAdapter
import com.example.appthitracnghiem.ui.home.history.saved.adapter.HistorySubjectAdapter
import kotlinx.android.synthetic.main.fragment_history_saved.*
import kotlinx.android.synthetic.main.fragment_history_subject_saved.*

@Suppress("DEPRECATION")
class FragmentHistorySubjectSaved : Fragment() {
    lateinit var listSubjectHistory: ArrayList<Test>
    lateinit var historySubjectAdapter: HistorySubjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linear: LinearLayoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        rcvSubjectHistory.layoutManager = linear

        listSubjectHistory = arrayListOf()
        listSubjectHistory.add(Test(1,R.drawable.icon_test_subject,"Toán học","Khoa tự nhiên • 123 đề."))
        listSubjectHistory.add(Test(2,R.drawable.icon_test_subject,"Vật Lý","Khoa tự nhiên • 144 đề"))
        listSubjectHistory.add(Test(3,R.drawable.icon_test_subject,"Sinh học","Khoa tự nhiên • 120 đề"))

        historySubjectAdapter = HistorySubjectAdapter(requireActivity(),listSubjectHistory)
        rcvSubjectHistory.adapter = historySubjectAdapter

        click()
    }

    private fun click() {
        backHistorySubjectSaved.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_subject_saved, container, false)
    }
}