package com.example.appthitracnghiem.ui.home.history.saved.subject

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistorySubjectSavedBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.subject.adapter.HistorySubjectAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistorySubjectSaved : BaseFragment<HistorySubjectSavedViewModel>() {
    private var _binding: FragmentHistorySubjectSavedBinding? = null
    private val binding get() = _binding!!
    lateinit var historySubjectAdapter: HistorySubjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle(getString(R.string.dialog_title_notice))
        loading.setMessage(getString(R.string.loading_please_wait))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val departmentId: Int = requireArguments().getInt("department_id_saved")
        viewModel.saveSubject(RequestSubjectSaved(userId, departmentId))

        viewModel.listSubjectSaved.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            binding.rcvSubjectHistory.layoutManager = linear
            historySubjectAdapter = HistorySubjectAdapter(requireActivity(),it)
            binding.rcvSubjectHistory.adapter = historySubjectAdapter
        }
    }

    private fun initUi() {
        binding.txtDepartmentSaved.text = requireArguments().getString("department_name_saved")

        binding.backHistorySubjectSaved.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySubjectSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}