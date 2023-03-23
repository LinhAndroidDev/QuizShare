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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSetting : BaseFragment<EmptyViewModel>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backSetting.setOnClickListener {
            activity?.finish()
        }

        setText()

        updateInfo.setOnClickListener {
            replaceFragment(FragmentUpdateInfor())
        }

        changeEmail.setOnClickListener {
            replaceFragment(FragmentUpdateEmail())
        }

        changePassWord.setOnClickListener {
            replaceFragment(FragmentSettingNewPassword())
        }

        setBottomShare()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
            val intent: Intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        cancel.setOnClickListener {
            bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            layoutLogoutCover.visibility = View.GONE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSetting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}