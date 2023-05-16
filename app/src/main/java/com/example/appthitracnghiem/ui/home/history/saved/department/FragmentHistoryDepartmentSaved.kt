package com.example.appthitracnghiem.ui.home.history.saved.department

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.department.adapter.HistoryDepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_history_saved.*

@Suppress("DEPRECATION")
class FragmentHistoryDepartmentSaved : BaseFragment<HistoryDepartmentSavedViewModel>() {
    lateinit var historyDepartmentAdapter: HistoryDepartmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setTitle("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestSavedDepartment = RequestSavedDepartment(userId)
        viewModel.getDepartmentSaved(header, requestSavedDepartment)

        viewModel.listDepartmentSaved.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
            rcvDepartmentHistory.layoutManager = linear
            historyDepartmentAdapter = HistoryDepartmentAdapter(requireActivity(),it)
            rcvDepartmentHistory.adapter = historyDepartmentAdapter
        }
    }

    private fun initUi() {
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