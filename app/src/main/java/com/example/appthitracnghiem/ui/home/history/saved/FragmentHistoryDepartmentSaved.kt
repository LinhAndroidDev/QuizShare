package com.example.appthitracnghiem.ui.home.history.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.adapter.HistoryDepartmentAdapter
import kotlinx.android.synthetic.main.fragment_history_saved.*

@Suppress("DEPRECATION")
class FragmentHistoryDepartmentSaved : BaseFragment<EmptyViewModel>() {
    lateinit var listDepartmentHistory: ArrayList<Test>
    lateinit var historyDepartmentAdapter: HistoryDepartmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linear: LinearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        rcvDepartmentHistory.layoutManager = linear

        listDepartmentHistory = arrayListOf()
        listDepartmentHistory.add(Test(1,R.drawable.icon_test_subject,"Khoa Tự Nhiên","Tóan, Sinh, Lý, Hóa,..."))
        listDepartmentHistory.add(Test(2,R.drawable.icon_test_subject,"Khoa Khoa Học","Nghiên cứu, Sáng tạo,.."))
        listDepartmentHistory.add(Test(3,R.drawable.icon_test_subject,"Khoa Nhạc","Thanh nhạc, Dụng cụ,..."))
        listDepartmentHistory.add(Test(4,R.drawable.icon_test_subject,"Khoa Thể Chất","Võ, Điền Kinh, Bóng đá,..."))
        listDepartmentHistory.add(Test(5,R.drawable.icon_test_subject,"Khoa Truyền Thông","Báo chí, Đa phương tiện,..."))
        listDepartmentHistory.add(Test(6,R.drawable.icon_test_subject,"Khoa Du Lịch","Thế giới, tour,..."))

        historyDepartmentAdapter = HistoryDepartmentAdapter(requireActivity(),listDepartmentHistory)
        rcvDepartmentHistory.adapter = historyDepartmentAdapter

        click()
    }

    private fun click() {
        backHistorySaved.setOnClickListener {
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
        return inflater.inflate(R.layout.fragment_history_saved, container, false)
    }
}