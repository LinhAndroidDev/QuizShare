package com.example.appthitracnghiem.ui.register

import android.app.ProgressDialog
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.RegisterResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.login.ValidateModel
import com.example.appthitracnghiem.utils.Email
import kotlinx.android.synthetic.main.fragment__register.*
import retrofit2.Call
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class RegisterViewModel : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val successRegisterLiveData = MutableLiveData<Boolean>()
    val validateLiveData = MutableLiveData<ValidateModel>()

    private fun validateRegister(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String,
        strPasswordRepeat: String
    ): ValidateModel {
        return if (strName.isEmpty() || strYearOfBirth.isEmpty() || strEmail.isEmpty() || strPhone.isEmpty() || strPassword.isEmpty() || strPasswordRepeat.isEmpty()) {
            ValidateModel(false, R.string.txt_notification_register, R.color.color_green)
        } else {
            val email: Email = Email(strEmail, strPassword)
            if (!email.isValidEmail()) {
                ValidateModel(false, R.string.txt_warning_login, R.color.color_red)
            }
            if (!email.isPassword()) {
                ValidateModel(false, R.string.txt_warning_password, R.color.color_red)
            }
            if (!Patterns.PHONE.matcher(strPhone)
                    .matches() || strPhone.length < 10
            ) {
                ValidateModel(false, R.string.txt_warning_phone, R.color.color_red)
            }
            if (strPassword != strPasswordRepeat) {
                ValidateModel(false, R.string.txtEnterRepeatPassword, R.color.color_red)
            }
            ValidateModel(true, -1, -1)
        }
    }

    fun register(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String,
        strPasswordRepeat: String
    ){
        val validateModel = validateRegister(strEmail,strName,strPhone,strYearOfBirth,strPassword,strPasswordRepeat)
        validateLiveData.value = validateModel
        if(validateModel.isValidate){
            requestRegister(strEmail,strName,strPhone,strYearOfBirth,strPassword)
        }
    }

    private fun requestRegister(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String
    ) {
        loadingLiveData.value = true

        val requestRegister = RequestRegister(strEmail,strName,strPhone,strYearOfBirth,strPassword)

        ApiClient.shared()
            .registerUser(requestRegister)
            .enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    loadingLiveData.value = false
                    if (response.isSuccessful) {
                        when (response.body()?.statusCode) {
                            ApiClient.STATUS_CODE_SUCCESS -> {
                                successRegisterLiveData.value = true
                            }
                            400 -> {
                                errorApiLiveData.value = "Tài khoản đã tồn tại"
                            }
                            500 -> {
                                errorApiLiveData.value = "Server not responding"
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<RegisterResponse>,
                    t: Throwable
                ) {
                    loadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }
            })
    }
}