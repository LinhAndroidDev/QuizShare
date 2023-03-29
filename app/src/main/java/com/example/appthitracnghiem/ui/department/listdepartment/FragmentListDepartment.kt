package com.example.appthitracnghiem.ui.department.listdepartment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter
import kotlinx.android.synthetic.main.fragment_list_department.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListDepartment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentListDepartment : BaseFragment<EmptyViewModel>() {
    lateinit var listDetailDepartment : List<DetailDepartment>
    lateinit var listDepartmentAdapter: ListDepartmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()

    }

    private fun click() {
        backDepartment.setOnClickListener{
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_department, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}