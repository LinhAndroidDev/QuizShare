package com.example.appthitracnghiem.ui.home.profile.setting

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.profile.setting.email.FragmentUpdateEmail
import com.example.appthitracnghiem.ui.home.profile.setting.info.FragmentUpdateInfor
import com.example.appthitracnghiem.ui.home.profile.setting.password.FragmentSettingNewPassword
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_logout.*

@Suppress("DEPRECATION")
class FragmentSetting : BaseFragment<SettingViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Đang vô hiệu hoá tài khoản...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                viewModel.confirmLoggedOut()
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                Toast.makeText(
                    requireActivity(),
                    "Đã vô hiệu hoá tài khoản",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initUi() {

        emailSetting.text = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_EMAIL,"").toString()

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

        deleteAccount.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireActivity())
            alertDialog.setTitle("Cảnh báo!!")
            alertDialog.setIcon(R.drawable.icon_app_thitn)
            alertDialog.setMessage("Nếu bạn xoá tài khoản này sẽ bị vô hiệu hoá?")
            alertDialog.setPositiveButton("Vẫn xoá") { _, _ ->
                val header = viewModel.mPreferenceUtil.defaultPref()
                    .getString(PreferenceKey.AUTHORIZATION,"").toString()
                val userId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                val requestUnPublishUser = RequestUnPublishUser(userId)
                viewModel.unPublishUser(header, requestUnPublishUser)
            }
            alertDialog.setNegativeButton("Không") { _, _ -> }
            alertDialog.show()
        }

        setBottomShare()

        setText()
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
        bottomShareBehavior.isDraggable = false
        layoutLogoutCover.setOnTouchListener { v, event -> true }

        logout.setOnClickListener {
            if (bottomShareBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                layoutLogoutCover.visibility = View.VISIBLE
            } else {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                layoutLogoutCover.visibility = View.GONE
            }
        }

        layoutLogoutCover.setOnTouchListener { v, event ->
            when(event.actionMasked){
                MotionEvent.ACTION_UP->{
                    layoutLogoutCover.visibility = View.GONE
                    bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            true
        }

        logoutNow.setOnClickListener {
            viewModel.confirmLoggedOut()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        cancel.setOnClickListener {
            bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            layoutLogoutCover.visibility = View.GONE
        }
    }
}