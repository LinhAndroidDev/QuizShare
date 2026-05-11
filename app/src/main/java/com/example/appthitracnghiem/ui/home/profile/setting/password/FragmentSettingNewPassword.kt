package com.example.appthitracnghiem.ui.home.profile.setting.password

import android.app.ProgressDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentSettingNewPasswordBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentSettingNewPassword : BaseFragment<ChangePasswordViewModel>() {
    private var _binding: FragmentSettingNewPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireActivity(),"Bạn đã cập nhật mật khẩu thành công",Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
        }
    }

    private fun initUi() {
        binding.backUpdateNewPassWord.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.showUpdatePassWord.setOnClickListener {
            hidePassword(binding.edtPasswordUpdate, binding.showUpdatePassWord)
        }

        binding.showRepeatUpdatePassWord.setOnClickListener {
            hidePassword(binding.edtRepeatPasswordUpdate, binding.showRepeatUpdatePassWord)
        }

        binding.resetPasswordSetting.setOnClickListener {
            val strPassword = binding.edtPasswordUpdate.text.toString()
            val strPasswordRepeat = binding.edtRepeatPasswordUpdate.text.toString()

            if(strPassword.isEmpty() || strPasswordRepeat.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập đầy đử thông tin",Toast.LENGTH_SHORT).show()
            } else if(strPassword.length < 7 || strPasswordRepeat.length < 7){
                Toast.makeText(requireActivity(),"Mật khẩu phải lớn hơn 6 kí tự",Toast.LENGTH_SHORT).show()
            }else if(strPassword != strPasswordRepeat){
                Toast.makeText(requireActivity(),"Mật khẩu nhập lại không đúng",Toast.LENGTH_SHORT).show()
            } else{
                val userId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                viewModel.changePassword(RequestChangePassword(strPassword, strPasswordRepeat, userId))
            }
        }

    }

    private fun hidePassword(password: EditText, hide: ImageView) {
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            password.transformationMethod = null
            hide.setBackgroundResource(R.drawable.icon_show_password_grey)
        } else if (password.transformationMethod == null) {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            hide.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}