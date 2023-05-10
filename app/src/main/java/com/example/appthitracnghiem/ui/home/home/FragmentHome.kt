package com.example.appthitracnghiem.ui.home.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeViewModel
import com.example.appthitracnghiem.ui.home.RequestUserInfo
import com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment
import com.example.appthitracnghiem.ui.home.profile.setting.changeavatar.ChangeAvatarActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_loading.*

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

        initUi()

        val accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestUserInfo: RequestUserInfo = RequestUserInfo(userId)
        viewModel.getDataUserInfo(accessToken, requestUserInfo)
    }

    private fun initUi() {
        avatarUseHome.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), ChangeAvatarActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun bindData() {
        super.bindData()

        viewModel.nameUserLiveData.observe(viewLifecycleOwner) {
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_NAME,it)
                .apply()
            txtNameUserHome.text = it
        }

        viewModel.avartarUserLiveData.observe(viewLifecycleOwner) {
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_AVATAR,it)
                .apply()
            Picasso.get().load("https://img2.thuthuatphanmem.vn/uploads/2019/01/04/hinh-anh-dep-co-gai-de-thuong_025103410.jpg")
                .placeholder(R.drawable.loadimage)
                .error(R.drawable.icon_error)
                .into(avatarUseHome)
        }
    }

    private fun setText() {
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