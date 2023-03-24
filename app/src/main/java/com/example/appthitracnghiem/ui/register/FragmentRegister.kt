package com.example.appthitracnghiem.ui.register

import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.RegisterResponse
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.Email
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment__register.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRegister.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentRegister : BaseFragment<RegisterViewModel>() {
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        click()

        setText()
    }

    private fun click() {
        hidePasswordRegister.setOnClickListener {
            hidePassword(passwordRegister, hidePasswordRegister)
        }

        hidePasswordRegisterRepeat.setOnClickListener {
            hidePassword(passwordRegisterRepeat, hidePasswordRegisterRepeat)
        }

        registerAccount.setOnClickListener {
            register()
        }

        loginNow.setOnClickListener {
            activity?.finish()
        }
    }

    private fun register() {
        val progressDialog: ProgressDialog = ProgressDialog(requireActivity())
        progressDialog.setMessage("Đang cập nhật tài khoản")
        val strName = edtEnterNameRegister.text.toString().trim()
        val strYearOfBirth = edtEnterYearOfBirthRegister.text.toString().trim()
        val strEmail = edtEnterEmailRegister.text.toString().trim()
        val strPhone = edtPhoneRegister.text.toString().trim()
        val strPassword = passwordRegister.text.toString().trim()
        val strPasswordRepeat = passwordRegisterRepeat.text.toString().trim()

        if (strName.isEmpty() || strYearOfBirth.isEmpty() || strEmail.isEmpty() || strPhone.isEmpty() || strPassword.isEmpty() || strPasswordRepeat.isEmpty()) {
            setNote(R.string.txt_notification_register, R.color.color_green)
            return
        }
        val email: Email = Email(strEmail, strPassword)
        if (!email.isValidEmail()) {
            setNote(R.string.txt_warning_login, R.color.color_red)
            return
        }
        if (!email.isPassword()) {
            setNote(R.string.txt_warning_password, R.color.color_red)
            return
        }
        if (!Patterns.PHONE.matcher(strPhone)
                .matches() || strPhone.length < 10
        ) {
            setNote(R.string.txt_warning_phone, R.color.color_red)
            return
        }
        if (strPassword != strPasswordRepeat) {
            setNote(R.string.txtEnterRepeatPassword, R.color.color_red)
            return
        }
        progressDialog.show()

        val requestRegister = RequestRegister(strEmail,strName,strPhone,strYearOfBirth.toInt(),strPassword)

        viewModelGeneral.postRetrofit.create(ApiService::class.java)
            .registerUser(requestRegister)
            .enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    progressDialog.dismiss()
                    if (response.isSuccessful) {
                        when (response.body()?.statusCode) {
                            ApiClient.STATUS_CODE_SUCCESS -> {
                                val fragmentCondition: FragmentCondition =
                                    FragmentCondition()
                                val fm: FragmentTransaction =
                                    requireActivity().supportFragmentManager.beginTransaction()
                                fm.addToBackStack("Fragment_Register")
                                fm.replace(
                                    R.id.registerContainerID,
                                    fragmentCondition
                                ).commit()
                            }
                            2 -> {
                                Toast.makeText(
                                    requireActivity(),
                                    "Tài khoản đã tồn tại",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    requireActivity(),
                                    "Lỗi",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<RegisterResponse>,
                    t: Throwable
                ) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        requireActivity(),
                        "Lỗi kết nối Server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun setText() {
        val strText: String = getString(R.string.txtNoteRegister)
        val text: SpannableString = SpannableString(strText)
        val boldStart: StyleSpan = StyleSpan(Typeface.BOLD)
        val boldEnd: StyleSpan = StyleSpan(Typeface.BOLD)
        text.setSpan(boldStart, 43, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text.setSpan(boldEnd, 57, 74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textString.text = text
    }

    fun setNote(string: Int, color: Int) {
        val circle: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_shake)
        warningRegister.text = getString(string)
        warningRegister.setTextColor(resources.getColor(color))
        warningRegister.visibility = View.VISIBLE
        warningRegister.startAnimation(circle)
    }

    private fun hidePassword(password: EditText, hide: ImageView) {
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            password.transformationMethod = null
            hide.setBackgroundResource(R.drawable.icon_show_password_grey)
        } else if (newPasswordCreate.transformationMethod == null) {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            hide.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__register, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return true
    }
}