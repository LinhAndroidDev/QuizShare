package com.example.appthitracnghiem.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.LoginResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.Email
import com.example.appthitracnghiem.utils.PreferenceKey
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val successLoginLiveData = MutableLiveData<Boolean>()
    val noteLiveData = MutableLiveData<ValidateModel>()

    fun isFirstInstallApp(): Boolean {
        return mPreferenceUtil.defaultPref()
            .getBoolean(PreferenceKey.KEY_FIRST_INSTALL, true)
    }

    fun clearFirstInstallApp() {
        mPreferenceUtil.defaultPref().edit()
            .putBoolean(PreferenceKey.KEY_FIRST_INSTALL, false).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return mPreferenceUtil.defaultPref()
            .getBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false)
    }

    private fun validateLogin(strEmail: String, strPassword: String): ValidateModel {
        return if (strEmail.isEmpty() || strPassword.isEmpty()) {
            ValidateModel(false, R.string.txt_notification_login, R.color.color_green)
        } else {
            val email: Email = Email(strEmail, strPassword)
            if (!email.isValidEmail()) {
                ValidateModel(false, R.string.txt_warning_login, R.color.color_red)
            } else {
                if (!email.isPassword()) {
                    ValidateModel(false, R.string.txt_warning_password, R.color.color_red)
                } else {
                    ValidateModel(true, -1, -1)
                }
            }
        }
    }

    fun login(strEmail: String, strPassword: String) {
        val noteModel = validateLogin(strEmail, strPassword)
        noteLiveData.value = noteModel
        if (noteModel.isValidate) {
            requestLogin(strEmail, strPassword)
        }
    }

    private fun requestLogin(strEmail: String, strPassword: String) {
        loadingLiveData.value = true
        val requestBodyStrEmail: RequestBody =
            RequestBody.create("multipart/from-data".toMediaTypeOrNull(), strEmail)
        val requestBodyStrPassword: RequestBody = RequestBody.create(
            "multipart/from-data".toMediaTypeOrNull(),
            strPassword
        )
        ApiClient.shared()
            .loginUser(requestBodyStrEmail, requestBodyStrPassword)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    loadingLiveData.value = false
                    if (response.isSuccessful) {
                        if (response.body()?.statusCode == ApiClient.STATUS_CODE_SUCCESS) {
                            successLoginLiveData.value = true
                            mPreferenceUtil.defaultPref().edit()
                                .putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, true).apply()
                        } else {
                            val errorMessage = response.body()?.message
                                ?: "Email hoặc mật khẩu không chính xác"
                            errorApiLiveData.value = errorMessage
                        }
                    } else {
                        errorApiLiveData.value = "Lỗi kết nối Server ${response.code()}"
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponse>,
                    t: Throwable
                ) {
                    loadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }
            })
    }
}