package com.example.appthitracnghiem.ui.home.history.saved.test

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryTestSavedBinding
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter
import com.example.appthitracnghiem.ui.home.history.test.general.RequestExamHistory
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistoryTestSaved : BaseFragment<TestSavedViewModel>() {
    private var _binding: FragmentHistoryTestSavedBinding? = null
    private val binding get() = _binding!!
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
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val subjectId = requireArguments().getInt("subject_saved_id")
        viewModel.savedTest(RequestTestSaved(userId, subjectId, 1, 3, "asc"))

        viewModel.listTestSavedLiveData.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            binding.rcvTestHistorySaved.layoutManager = linear
            if(it != null){
                historyTestAdapter = HistoryTestAdapter(requireActivity(),it)
                binding.rcvTestHistorySaved.adapter = historyTestAdapter
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        viewModel.departmentTitleLiveData.observe(viewLifecycleOwner){
            department = it
            binding.txtTestSaved.text = "$subject | $department"
        }
        viewModel.testTitleLiveData.observe(viewLifecycleOwner){
            subject = it
            binding.txtTestSaved.text = "$subject | $department"
        }

        binding.backHistoryTestSaved.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.menuHistoryTestSaved.setOnClickListener {
            showPopupMenu(binding.menuHistoryTestSaved,R.layout.popup_list_test,0, 0, Gravity.BOTTOM)
        }
    }

    private fun showPopupMenu(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val time: LinearLayout = popUpView.findViewById(R.id.flowTime)
        val numberCreate: LinearLayout = popUpView.findViewById(R.id.flowNumberCreate)
        val subject: LinearLayout = popUpView.findViewById(R.id.flowSubject)

        time.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID,0)
            val subjectId = requireArguments().getInt("subject_saved_id")
            viewModel.savedTest(RequestTestSaved(userId, subjectId, 1, 1, "asc"))
        }

        numberCreate.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID,0)
            val subjectId = requireArguments().getInt("subject_saved_id")
            viewModel.savedTest(RequestTestSaved(userId, subjectId, 1, 2, "asc"))
        }

        subject.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID,0)
            val subjectId = requireArguments().getInt("subject_saved_id")
            viewModel.savedTest(RequestTestSaved(userId, subjectId, 1, 3, "asc"))
        }

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryTestSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}