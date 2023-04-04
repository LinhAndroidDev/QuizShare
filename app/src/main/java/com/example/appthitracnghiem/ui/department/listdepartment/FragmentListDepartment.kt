package com.example.appthitracnghiem.ui.department.listdepartment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
@Suppress("DEPRECATION")
class FragmentListDepartment : BaseFragment<EmptyViewModel>() {
    lateinit var listDetailDepartment : MutableList<DetailDepartment>
    lateinit var listDepartmentAdapter: ListDepartmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listDetailDepartment = mutableListOf()
        listDetailDepartment.add(DetailDepartment(1,"Khoa tự nhiên"))
        listDetailDepartment.add(DetailDepartment(2,"Khoa khoa học"))
        listDetailDepartment.add(DetailDepartment(3,"Khoa sinh học"))

        listDepartmentAdapter = ListDepartmentAdapter(requireActivity(),listDetailDepartment)

        val linear : LinearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        recycleDetailListDepartment.layoutManager = linear
        recycleDetailListDepartment.adapter = listDepartmentAdapter

        click()

    }

    private fun click() {
        backDepartment.setOnClickListener{
            activity?.onBackPressed()
        }

        searchDepartment.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                listDepartmentAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
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