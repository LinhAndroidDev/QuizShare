package com.example.appthitracnghiem.ui.home.profile

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.profile.setting.SettingActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentProfile : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        percentColum(30, 80, 60)

        progressNumberDay.apply {
            progressMax = 100f
            setProgressWithAnimation(80f,3000)
        }

        Picasso.get().load("https://img2.thuthuatphanmem.vn/uploads/2019/01/04/hinh-anh-dep-co-gai-de-thuong_025103410.jpg")
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .into(avatarUserProfile)

//        (activity as HomeActivity).hideTabBar(scrollProfile)

        click()

        setText()
    }

    private fun click() {
        backProfile.setOnClickListener {
            activity?.onBackPressed()
        }

        setting.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
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