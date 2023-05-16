package com.example.appthitracnghiem.ui.home.home.system

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.system.adapter.DepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_system.*

class FragmentSystem : BaseFragment<SystemViewModel>() {
    private lateinit var adapterFromSystem: DepartmentAdapter
    lateinit var accessToken: String
    var userId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val linearLayoutManager =
            object : LinearLayoutManager(requireActivity()) { override fun canScrollVertically() = false }
        recycleListLiveQuizzes.layoutManager = linearLayoutManager

        setText()
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingData.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading && recycleListLiveQuizzes.adapter == null){
                loadingDepartment.visibility = View.VISIBLE
            }else{
                loadingDepartment.visibility = View.GONE
            }
        }

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){ listDepart ->
            adapterFromSystem = DepartmentAdapter(listDepart,requireActivity())
            recycleListLiveQuizzes.adapter = adapterFromSystem
        }

        accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestGetListDepartment = RequestGetListDepartment(userId,"")
        viewModel.getDataDepartment(accessToken,requestGetListDepartment)
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textQuizHome.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system, container, false)
    }
}