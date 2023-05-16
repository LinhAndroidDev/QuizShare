package com.example.appthitracnghiem.ui.home.profile.setting.password

import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_setting_new_password.*

@Suppress("DEPRECATION")
class FragmentSettingNewPassword : BaseFragment<ChangePasswordViewModel>() {

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
        backUpdateNewPassWord.setOnClickListener {
            activity?.onBackPressed()
        }

        showUpdatePassWord.setOnClickListener {
            hidePassword(edtPasswordUpdate,showUpdatePassWord)
        }

        showRepeatUpdatePassWord.setOnClickListener {
            hidePassword(edtRepeatPasswordUpdate,showRepeatUpdatePassWord)
        }

        resetPasswordSetting.setOnClickListener {
            val strPassword = edtPasswordUpdate.text.toString()
            val strPasswordRepeat = edtRepeatPasswordUpdate.text.toString()

            if(strPassword.isEmpty() || strPasswordRepeat.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập đầy đử thông tin",Toast.LENGTH_SHORT).show()
            } else if(strPassword.length < 7 || strPasswordRepeat.length < 7){
                Toast.makeText(requireActivity(),"Mật khẩu phải lớn hơn 6 kí tự",Toast.LENGTH_SHORT).show()
            }else if(strPassword != strPasswordRepeat){
                Toast.makeText(requireActivity(),"Mật khẩu nhập lại không đúng",Toast.LENGTH_SHORT).show()
            } else{
                val header = viewModel.mPreferenceUtil.defaultPref()
                    .getString(PreferenceKey.AUTHORIZATION, "").toString()
                val userId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                val requestChangePassword = RequestChangePassword(strPassword, strPasswordRepeat, userId)

                viewModel.changePassword(header, requestChangePassword)
            }
        }

        setText()
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtMatKhauMoi.typeface = semibold
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_new_password, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}