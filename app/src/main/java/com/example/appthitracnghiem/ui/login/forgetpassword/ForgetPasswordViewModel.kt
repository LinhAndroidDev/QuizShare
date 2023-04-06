package com.example.appthitracnghiem.ui.login.forgetpassword

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.EmailVerificationResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ForgetPasswordViewModel : BaseViewModel() {
    val verification = MutableLiveData<Boolean>()

    fun checkEmail(requestEmailVerification: RequestEmailVerification){
        ApiClient.shared().emailVerification(requestEmailVerification)
            .enqueue(object : retrofit2.Callback<EmailVerificationResponse> {
                override fun onResponse(
                    call: Call<EmailVerificationResponse>,
                    response: Response<EmailVerificationResponse>
                ) {
                    if(response.body()?.statusCode == ApiClient.STATUS_CODE_SUCCESS){
                        verification.value = response.body()?.result
                    }
                    if(response.body()?.statusCode == ApiClient.STATUS_USER_EXIST){
                        errorApiLiveData.value = response.body()?.message
                    }
                }

                override fun onFailure(call: Call<EmailVerificationResponse>, t: Throwable) {
                    errorApiLiveData.value = t.message
                }

            })
    }
}