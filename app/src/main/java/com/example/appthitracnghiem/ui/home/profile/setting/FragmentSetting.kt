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
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentSettingBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.profile.setting.email.FragmentUpdateEmail
import com.example.appthitracnghiem.ui.home.profile.setting.info.FragmentUpdateInfor
import com.example.appthitracnghiem.ui.home.profile.setting.password.FragmentSettingNewPassword
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.android.material.bottomsheet.BottomSheetBehavior

@Suppress("DEPRECATION")
class FragmentSetting : BaseFragment<SettingViewModel>() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

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

        binding.emailSetting.text = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_EMAIL,"").toString()

        binding.backSetting.setOnClickListener {
            activity?.finish()
        }

        binding.updateInfo.setOnClickListener {
            replaceFragment(FragmentUpdateInfor())
        }

        binding.changeEmail.setOnClickListener {
            replaceFragment(FragmentUpdateEmail())
        }

        binding.changePassWord.setOnClickListener {
            replaceFragment(FragmentSettingNewPassword())
        }

        binding.deleteAccount.setOnClickListener {
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
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        binding.txtSetting.typeface = semibold
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setBottomShare() {
        val layoutLogout = binding.root.findViewById<RelativeLayout>(R.id.layoutLogout)
        val logoutNow = binding.root.findViewById<TextView>(R.id.logoutNow)
        val cancel = binding.root.findViewById<CardView>(R.id.cancel)
        val bottomShareBehavior = BottomSheetBehavior.from(layoutLogout)
        bottomShareBehavior.isDraggable = false
        binding.layoutLogoutCover.setOnTouchListener { _, _ -> true }

        binding.logout.setOnClickListener {
            if (bottomShareBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.layoutLogoutCover.visibility = View.VISIBLE
            } else {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.layoutLogoutCover.visibility = View.GONE
            }
        }

        binding.layoutLogoutCover.setOnTouchListener { _, event ->
            when(event.actionMasked){
                MotionEvent.ACTION_UP->{
                    binding.layoutLogoutCover.visibility = View.GONE
                    bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            true
        }

        logoutNow.setOnClickListener {
            viewModel.confirmLoggedOut()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_AVATAR,"")
                .apply()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        cancel.setOnClickListener {
            bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.layoutLogoutCover.visibility = View.GONE
        }
    }
}