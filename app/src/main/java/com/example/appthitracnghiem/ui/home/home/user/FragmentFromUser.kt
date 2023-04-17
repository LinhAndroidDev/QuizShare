package com.example.appthitracnghiem.ui.home.home.user

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import com.example.appthitracnghiem.ui.home.home.system.adapter.DepartmentAdapter
import com.example.appthitracnghiem.ui.home.home.user.adapter.FromUserAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_from_user.*
import kotlinx.android.synthetic.main.fragment_system.*

class FragmentFromUser : BaseFragment<FromUserViewModel>() {
    private lateinit var adapterFromUser: FromUserAdapter
    lateinit var accessToken: String
    var user_id: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recycleListFromUser.layoutManager = linearLayoutManager

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
        user_id = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestGetListDepartment = RequestGetListDepartment(user_id,"")
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