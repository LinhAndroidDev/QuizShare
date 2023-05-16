package com.example.appthitracnghiem.ui.home.history.saved.test

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_history_subject_saved.*
import kotlinx.android.synthetic.main.fragment_history_test_saved.*

@Suppress("DEPRECATION")
class FragmentHistoryTestSaved : BaseFragment<TestSavedViewModel>() {
    lateinit var historyTestAdapter: HistoryTestAdapter
    companion object{
        var department: String = ""
        var subject: String = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thong bao")
        loading.setMessage("Please wait...")
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
        val subjectId = requireArguments().getInt("subject_saved_id")
        val requestTestSaved = RequestTestSaved(userId, subjectId, 0, 3, "asc")
        viewModel.savedTest(header, requestTestSaved)

        viewModel.listTestSavedLiveData.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            rcvTestHistorySaved.layoutManager = linear
            if(it != null){
                historyTestAdapter = HistoryTestAdapter(requireActivity(),it)
                rcvTestHistorySaved.adapter = historyTestAdapter
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        viewModel.departmentTitleLiveData.observe(viewLifecycleOwner){
            department = it
            txtTestSaved.text = "$subject | $department"
        }
        viewModel.testTitleLiveData.observe(viewLifecycleOwner){
            subject = it
            txtTestSaved.text = "$subject | $department"
        }

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