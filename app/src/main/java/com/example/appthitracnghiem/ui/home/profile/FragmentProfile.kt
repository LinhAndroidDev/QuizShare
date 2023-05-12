package com.example.appthitracnghiem.ui.home.profile

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.profile.setting.SettingActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

@Suppress("DEPRECATION")
class FragmentProfile : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        percentColum(30, 80, 60)

        progressNumberDay.apply {
            progressMax = 100f
            setProgressWithAnimation(80f, 3000)
        }

        Picasso.get()
            .load("https://img2.thuthuatphanmem.vn/uploads/2019/01/04/hinh-anh-dep-co-gai-de-thuong_025103410.jpg")
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .into(avatarUserProfile)

//        (activity as HomeActivity).hideTabBar(scrollProfile)

        initUi()

    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        };//  set status text dark
    }

    private fun initUi() {

        setStatusBar()

        backProfile.setOnClickListener {
            activity?.onBackPressed()
        }

        setting.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

        setText()
    }

    private fun percentColum(math: Int, science: Int, painting: Int) {
        progressMath.progress = math.toFloat()
        progressScience.progress = science.toFloat()
        progressPainting.progress = painting.toFloat()
    }

    private fun setText() {
        txtName.text = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_NAME, "")

        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtName.typeface = semibold
        txtProfile.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}