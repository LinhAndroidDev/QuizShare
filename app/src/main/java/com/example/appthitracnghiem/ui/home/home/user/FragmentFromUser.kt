package com.example.appthitracnghiem.ui.home.home.user

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentFromUserBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.ui.home.home.user.adapter.FromUserAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFromUser : BaseFragment<FromUserViewModel>() {
    private var _binding: FragmentFromUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterFromUser: FromUserAdapter
    var userId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val linearLayoutManager =
            object : LinearLayoutManager(requireActivity()) { override fun canScrollVertically() = false }
        binding.recycleListFromUser.layoutManager = linearLayoutManager

        binding.seeAllUser.setOnClickListener {
            val fragmentListDepartment = FragmentListDepartment()
            val fm: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentListDepartment).addToBackStack(null).commit()
        }

        setText()
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingFromUserData.observe(viewLifecycleOwner){ isLoading->
            if(isLoading){
                binding.loadingQuizFromUser.visibility = View.VISIBLE
            }else{
                binding.loadingQuizFromUser.visibility = View.INVISIBLE
            }
        }

        viewModel.listDepartmentFromUserLiveData.observe(viewLifecycleOwner){
            adapterFromUser = FromUserAdapter(it,requireActivity())
            binding.recycleListFromUser.adapter = adapterFromUser
        }

        userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        viewModel.getDataDepartmentFromUser(userId, "")
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        binding.textQuizHomeFromUser.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFromUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}