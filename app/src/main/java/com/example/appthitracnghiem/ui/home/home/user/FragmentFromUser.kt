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
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import com.example.appthitracnghiem.ui.home.home.user.adapter.FromUserAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_from_user.*
import kotlinx.android.synthetic.main.fragment_system.*

class FragmentFromUser : BaseFragment<FromUserViewModel>() {
    private lateinit var adapterFromUser: FromUserAdapter
    lateinit var accessToken: String
    var userId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val linearLayoutManager =
            object : LinearLayoutManager(requireActivity()) { override fun canScrollVertically() = false }
        recycleListFromUser.layoutManager = linearLayoutManager

        seeAllUser.setOnClickListener {
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
                loadingQuizFromUser.visibility = View.VISIBLE
            }else{
                loadingQuizFromUser.visibility = View.INVISIBLE
            }
        }

        viewModel.listDepartmentFromUserLiveData.observe(viewLifecycleOwner){
            adapterFromUser = FromUserAdapter(it,requireActivity())
            recycleListFromUser.adapter = adapterFromUser
        }

        accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestGetListDepartment = RequestGetListDepartment(userId,"")
        viewModel.getDataDepartmentFromUser(accessToken,requestGetListDepartment)
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textQuizHomeFromUser.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_from_user, container, false)
    }
}