package com.example.appthitracnghiem.ui.home.history.test.general

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamHistory
import com.example.appthitracnghiem.databinding.FragmentHistoryTestBinding
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistoryTest : BaseFragment<HistoryTestViewModel>() {
    private var _binding: FragmentHistoryTestBinding? = null
    private val binding get() = _binding!!
    private lateinit var testAdapter: HistoryTestAdapter
    lateinit var testAdapterUser: HistoryTestAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        viewModel.getExamHistory(RequestExamHistory(userId,5,0,1,"asc"))

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                (activity as HomeActivity).loadingVisible(true)
            }else{
                (activity as HomeActivity).loadingVisible(false)
            }
        }

        viewModel.listExamHistoryLiveData.observe(viewLifecycleOwner){ listExamHistory ->
            listExamHistory?.let {
                val linear = object : LinearLayoutManager(requireActivity()){
                    override fun canScrollVertically() = false
                }
                val linearUser = object : LinearLayoutManager(requireActivity()){
                    override fun canScrollVertically() = false
                }
                val listSystem: ArrayList<HistoryExam> = arrayListOf()
                val listUser: ArrayList<HistoryExam> = arrayListOf()
                for(i in 0 until it.size){
                    if(it[i].user_create.isNullOrBlank()){
                        listSystem.add(it[i])
                    }else{
                        listUser.add(it[i])
                    }
                }
                testAdapter = HistoryTestAdapter(requireActivity(),listSystem)
                binding.listTestFromSystem.layoutManager = linear
                binding.listTestFromSystem.adapter = testAdapter

                testAdapterUser = HistoryTestAdapter(requireActivity(),listUser)
                binding.listTestFromUser.layoutManager = linearUser
                binding.listTestFromUser.adapter = testAdapterUser
            }
        }
    }

    private fun initUi() {
        binding.backHistoryTest.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.menuHistoryTest.setOnClickListener {
            binding.menuHistoryTest.showPopupMenu()
        }
    }

    private fun View.showPopupMenu() {
        val popUpView: View = View.inflate(requireActivity(), R.layout.popup_history_test, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val date: LinearLayout = popUpView.findViewById(R.id.sortDate)
        val name: LinearLayout = popUpView.findViewById(R.id.sortName)
        val point: LinearLayout = popUpView.findViewById(R.id.sortPoint)

        date.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            viewModel.getExamHistory(RequestExamHistory(userId,5,0,1,"asc"))
        }

        name.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            viewModel.getExamHistory(RequestExamHistory(userId,5,0,2,"asc"))
        }

        point.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            viewModel.getExamHistory(RequestExamHistory(userId,5,0,3,"asc"))
        }

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(this, 0, 0, Gravity.BOTTOM)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}