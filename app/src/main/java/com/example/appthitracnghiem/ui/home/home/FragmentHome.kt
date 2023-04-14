package com.example.appthitracnghiem.ui.home.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.HomeViewModel
import com.example.appthitracnghiem.ui.home.RequestUserInfo
import com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentHome : BaseFragment<HomeViewModel>() {
    lateinit var viewPagerDepartment : ViewPagerDepartment

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerDepartment = ViewPagerDepartment(requireActivity().supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        pageDepartment.adapter = viewPagerDepartment

        tabDepartment.setupWithViewPager(pageDepartment)

//        (activity as HomeActivity).hideTabBar(scrollHome)

        setText()
    }

//    @SuppressLint("CommitPrefEdits")
//    override fun bindData() {
//        super.bindData()
//
//        viewModel.nameUserLiveData.observe(viewLifecycleOwner) {
//            viewModel.mPreferenceUtil.defaultPref()
//                .edit().putString(PreferenceKey.USER_NAME,it)
//                .apply()
//        }
//
//        viewModel.avartarUserLiveData.observe(viewLifecycleOwner) {
//            viewModel.mPreferenceUtil.defaultPref()
//                .edit().putString(PreferenceKey.USER_AVATAR,it)
//                .apply()
//        }
//
//        val accessToken = viewModel.mPreferenceUtil.defaultPref()
//            .getString(PreferenceKey.AUTHORIZATION,"").toString()
//        val userId = viewModel.mPreferenceUtil.defaultPref()
//            .getInt(PreferenceKey.USER_ID,0)
//        val requestUserInfo: RequestUserInfo = RequestUserInfo(userId)
//        viewModel.getDataUserInfo(accessToken, requestUserInfo)
//    }

    private fun setText() {
        txtNameUserHome.text = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_NAME,"")

        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textHome.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}