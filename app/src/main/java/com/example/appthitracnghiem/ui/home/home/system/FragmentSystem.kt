package com.example.appthitracnghiem.ui.home.home.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentSystemBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.ui.home.home.system.adapter.DepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.content.edit

@AndroidEntryPoint
class FragmentSystem : BaseFragment<SystemViewModel>() {
    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterFromSystem: DepartmentAdapter
    var userId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val linearLayoutManager =
            object : LinearLayoutManager(requireActivity()) { override fun canScrollVertically() = false }
        binding.recycleListLiveQuizzes.layoutManager = linearLayoutManager

        binding.seeAllSystem.setOnClickListener {
            viewModel.mPreferenceUtil.defaultPref().edit {
                remove(PreferenceKey.ID_DEPARTMENT)
            }
            val fragmentListDepartment = FragmentListDepartment()
            val fm: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentListDepartment).addToBackStack(null).commit()
        }
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingData.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading && binding.recycleListLiveQuizzes.adapter == null){
                binding.loadingDepartment.visibility = View.VISIBLE
            }else{
                binding.loadingDepartment.visibility = View.GONE
            }
        }

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){ listDepart ->
            adapterFromSystem = DepartmentAdapter(listDepart,requireActivity())
            binding.recycleListLiveQuizzes.adapter = adapterFromSystem
        }

        userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        viewModel.getDataDepartment(userId, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}