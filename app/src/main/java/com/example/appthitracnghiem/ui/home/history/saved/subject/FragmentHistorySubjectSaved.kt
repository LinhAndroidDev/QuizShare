package com.example.appthitracnghiem.ui.home.history.saved.subject

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.subject.adapter.HistorySubjectAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_history_saved.*
import kotlinx.android.synthetic.main.fragment_history_subject_saved.*

@Suppress("DEPRECATION")
class FragmentHistorySubjectSaved : BaseFragment<HistorySubjectSavedViewModel>() {
    lateinit var historySubjectAdapter: HistorySubjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION, "").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val departmentId: Int = requireArguments().getInt("department_id_saved")
        val requestSubjectSaved = RequestSubjectSaved(userId, departmentId)
        viewModel.saveSubject(header, requestSubjectSaved)

        viewModel.listSubjectSaved.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            rcvSubjectHistory.layoutManager = linear
            historySubjectAdapter = HistorySubjectAdapter(requireActivity(),it)
            rcvSubjectHistory.adapter = historySubjectAdapter
        }
    }

    private fun initUi() {
        txtDepartmentSaved.text = requireArguments().getString("department_name_saved")

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