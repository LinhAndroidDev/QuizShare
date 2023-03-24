package com.example.appthitracnghiem.ui.home.profile.setting

import android.annotation.SuppressLint
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
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_logout.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSetting : BaseFragment<SettingViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomShare()

        click()

        setText()
    }

    private fun click() {
        backSetting.setOnClickListener {
            activity?.finish()
        }

        updateInfo.setOnClickListener {
            replaceFragment(FragmentUpdateInfor())
        }

        changeEmail.setOnClickListener {
            replaceFragment(FragmentUpdateEmail())
        }

        changePassWord.setOnClickListener {
            replaceFragment(FragmentSettingNewPassword())
        }
    }

    private fun replaceFragment(fm: Fragment) {
        val fragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.addToBackStack(null)
        fragment.setCustomAnimations(
            R.anim.animation_scale_enter_right,
            R.anim.animation_scale_exit_left,
            R.anim.animation_scale_enter_left,
            R.anim.animation_scale_exit_right
        )
        fragment.replace(R.id.replaceFragmentSetting, fm).commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtSetting.typeface = semibold
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setBottomShare() {
        val bottomShareBehavior = BottomSheetBehavior.from(layoutLogout)
        layoutLogoutCover.setOnTouchListener { v, event -> true }

        layoutLogout.isEnabled = false

        logout.setOnClickListener {
            if (bottomShareBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                layoutLogoutCover.visibility = View.VISIBLE
            } else {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                layoutLogoutCover.visibility = View.GONE
            }
        }

        layoutLogout.setOnTouchListener { v, event ->
            bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            layoutLogoutCover.visibility = View.GONE
            true
        }

        logoutNow.setOnClickListener {
            viewModel.confirmLoggedOut()
            val intent: Intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        cancel.setOnClickListener {
            bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            layoutLogoutCover.visibility = View.GONE
        }
    }
}