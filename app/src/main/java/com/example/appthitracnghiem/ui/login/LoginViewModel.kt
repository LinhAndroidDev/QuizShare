package com.example.appthitracnghiem.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.LoginResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.Email
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val successLoginLiveData = MutableLiveData<Boolean>()
    val validateLiveData = MutableLiveData<ValidateModel>()
    val accessTokenLiveData = MutableLiveData<String>()
    val userIdLiveData = MutableLiveData<Int>()

    fun confirmLoggedIn(){
        mPreferenceUtil.defaultPref().edit()
            .putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, true).apply()
    }

    private fun validateLogin(strEmail: String, strPassword: String): ValidateModel {
        return if (strEmail.isEmpty() || strPassword.isEmpty()) {
            ValidateModel(false, R.string.txt_notification_login, R.color.color_green)
        } else {
            val email: Email = Email(strEmail, strPassword)
            if (email.isValidEmail() || email.isValidPhone()) {
                if (!email.isPassword()) {
                    ValidateModel(false, R.string.txt_warning_password, R.color.color_red)
                } else {
                    ValidateModel(true, -1, -1)
                }
            } else {
                ValidateModel(false, R.string.txt_warning_login, R.color.color_red)
            }
        }
    }

    fun login(strEmail: String, strPassword: String) {
        val validateModel = validateLogin(strEmail, strPassword)
        validateLiveData.value = validateModel
        if (validateModel.isValidate) {
            requestLogin(strEmail, strPassword)
        }
    }

    private fun requestLogin(strEmail: String, strPassword: String) {
        loadingLiveData.value = true
        val requestLogin = RequestLogin(strEmail,strPassword)
        ApiClient.shared()
            .loginUser(requestLogin)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    loadingLiveData.value = false
                    if (response.isSuccessful) {
                        response.body()?.let { body ->
                            if (body.statusCode == ApiClient.STATUS_CODE_SUCCESS) {
                                body.result?.let { result ->
                                    successLoginLiveData.value = true
                                    accessTokenLiveData.value = result.access_token
                                    if (result.user_id != null) {
                                        userIdLiveData.value = result.user_id
                                        confirmLoggedIn()
                                    } else {
                                        errorApiLiveData.value = "User id null"
                                    }

                                }
                            } else {
                                val errorMessage = response.body()?.message
                                    ?: "Email hoặc mật khẩu không chính xác"
                                errorApiLiveData.value = errorMessage
                            }

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
                    Log.e("TAG", "Error ${t.message}")
                }
            })
    }
}