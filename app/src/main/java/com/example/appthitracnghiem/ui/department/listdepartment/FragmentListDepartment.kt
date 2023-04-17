package com.example.appthitracnghiem.ui.department.listdepartment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_list_department.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListDepartment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentListDepartment : BaseFragment<ListDepartmentViewModel>() {
    lateinit var listDepartmentAdapter: ListDepartmentAdapter
    lateinit var accessToken: String
    var user_id: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val bundle: Bundle = requireArguments()
//        if(bundle.getString("title").toString().isNotEmpty()){
//            val title: String = bundle.getString("title").toString()
//            searchDepartment.setText(title)
//        }

        click()

    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingDepartmentLiveData.observe(viewLifecycleOwner){ isLoading->
            if(isLoading && recycleDetailListDepartment.adapter == null){
                loadingDepartmentInfo.visibility = View.VISIBLE
            }else{
                loadingDepartmentInfo.visibility = View.INVISIBLE
            }
        }

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){ listDepartment->
            listDepartmentAdapter = ListDepartmentAdapter(requireActivity(),listDepartment)
            val linear : LinearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
            recycleDetailListDepartment.layoutManager = linear
            recycleDetailListDepartment.adapter = listDepartmentAdapter
        }

        accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        user_id = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestDepartmentInfo: RequestDepartmentInfo = RequestDepartmentInfo(user_id)
        viewModel.getDataDepartmentDetail(accessToken, requestDepartmentInfo)
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