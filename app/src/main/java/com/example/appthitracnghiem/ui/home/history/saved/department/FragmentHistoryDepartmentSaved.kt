package com.example.appthitracnghiem.ui.home.history.saved.department

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistorySavedBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.department.adapter.HistoryDepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey

@Suppress("DEPRECATION")
class FragmentHistoryDepartmentSaved : BaseFragment<HistoryDepartmentSavedViewModel>() {
    private var _binding: FragmentHistorySavedBinding? = null
    private val binding get() = _binding!!
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
            binding.rcvDepartmentHistory.layoutManager = linear
            historyDepartmentAdapter = HistoryDepartmentAdapter(requireActivity(),it)
            binding.rcvDepartmentHistory.adapter = historyDepartmentAdapter
        }
    }

    private fun initUi() {
        binding.backHistorySaved.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}