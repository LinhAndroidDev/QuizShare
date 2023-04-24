package com.example.appthitracnghiem.ui.home.history.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.home.history.saved.adapter.HistorySubjectAdapter
import com.example.appthitracnghiem.ui.home.history.saved.adapter.HistoryTestAdapter
import kotlinx.android.synthetic.main.fragment_history_subject_saved.*
import kotlinx.android.synthetic.main.fragment_history_test_saved.*

@Suppress("DEPRECATION")
class FragmentHistoryTestSaved : Fragment() {
    lateinit var listTestHistory: ArrayList<Test>
    lateinit var historyTestAdapter: HistoryTestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linear: LinearLayoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        rcvTestHistorySaved.layoutManager = linear

        listTestHistory = arrayListOf()
        listTestHistory.add(Test(1,R.drawable.icon_test_subject,"Đề 1: Số phức","Lượt tạo: 102 | Người tạo: Hoàng"))
        listTestHistory.add(Test(2,R.drawable.icon_test_subject,"Đề 2: Bất phương trình","Lượt tạo: 102 | Người tạo: Hoàng"))
        listTestHistory.add(Test(3,R.drawable.icon_test_subject,"Đề 3: Hệ phương trình","Lượt tạo: 102 | Người tạo: Hoàng"))
        listTestHistory.add(Test(3,R.drawable.icon_test_subject,"Đề 4: Logarit","Lượt tạo: 102 | Người tạo: Hoàng"))
        listTestHistory.add(Test(3,R.drawable.icon_test_subject,"Đề 5: Cân bằng lực","Lượt tạo: 102 | Người tạo: Hoàng"))

        historyTestAdapter = HistoryTestAdapter(requireActivity(),listTestHistory)
        rcvTestHistorySaved.adapter = historyTestAdapter

        click()
    }

    private fun click() {
        backHistoryTestSaved.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_test_saved, container, false)
    }
}