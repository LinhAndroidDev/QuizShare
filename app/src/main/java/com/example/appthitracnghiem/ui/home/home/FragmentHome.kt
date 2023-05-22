@file:Suppress("DEPRECATION")

package com.example.appthitracnghiem.ui.home.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.HomeViewModel
import com.example.appthitracnghiem.ui.home.RequestUserInfo
import com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_loading.*

@Suppress("DEPRECATION")
class FragmentHome : BaseFragment<HomeViewModel>() {
    private lateinit var viewPagerDepartment : ViewPagerDepartment
    private var scrollPosition = 0.00

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerDepartment = ViewPagerDepartment(requireActivity().supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        pageDepartment.adapter = viewPagerDepartment

        tabDepartment.setupWithViewPager(pageDepartment)

//        (activity as HomeActivity).hideTabBar(scrollHome)

        initUi()

        val accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        val requestUserInfo = RequestUserInfo(userId)
        viewModel.getDataUserInfo(accessToken, requestUserInfo)
    }

    private fun initUi() {

        setStatusBar()

        avatarUseHome.setOnClickListener {
            val fragmentProfile = FragmentProfile()
            val fm = activity?.supportFragmentManager?.beginTransaction()
            fm?.add(R.id.changeIdHome,fragmentProfile)?.addToBackStack(null)?.commit()
            (activity as HomeActivity).clickAvatar()
        }

//        scrollHome.viewTreeObserver.addOnScrollChangedListener {
//            val scrollViewHeight = (scrollHome.getChildAt(0).bottom - scrollHome.height).toDouble()
//            val getScrollY: Double = scrollHome.scrollY.toDouble()
//            scrollPosition = 1 - getScrollY/scrollViewHeight
//
//            (activity as HomeActivity).visibleTaBar(scrollPosition)
//        }

        setText()
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.backgroundIntro)

        val decorView = window?.decorView //set status background black

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.systemUiVisibility?.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())!!
        } //set status text  light
    }

    @SuppressLint("CommitPrefEdits")
    override fun bindData() {
        super.bindData()

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                (activity as HomeActivity).loadingVisible(true)
            }else{
                (activity as HomeActivity).loadingVisible(false)
            }
        }

        viewModel.nameUserLiveData.observe(viewLifecycleOwner) {
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_NAME,it)
                .apply()
            txtNameUserHome.text = it
        }

        viewModel.avartarUserLiveData.observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                avatarUseHome.setImageResource(R.drawable.logo6)
            }else{
                viewModel.mPreferenceUtil.defaultPref()
                    .edit().putString(PreferenceKey.USER_AVATAR,it)
                    .apply()
                Picasso.get().load(it)
                    .placeholder(R.drawable.loadimage)
                    .error(R.drawable.logo6)
                    .into(avatarUseHome)
            }
        }
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textHome.typeface = semibold
    }

    internal fun scrollTop(){
        scrollHome.post {
            scrollHome.fling(0)
            scrollHome.smoothScrollTo(0, 0)
        }
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