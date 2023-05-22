package com.example.appthitracnghiem.ui.login.forgetpassword

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.EmailVerificationResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ForgetPasswordViewModel : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var isSuccessful = MutableLiveData<Boolean>()

    fun checkEmail(
        requestEmailVerification: RequestEmailVerification
    ){
        isLoading.value = true
        ApiClient.shared().emailVerification(requestEmailVerification)
            .enqueue(object : retrofit2.Callback<EmailVerificationResponse> {
                override fun onResponse(
                    call: Call<EmailVerificationResponse>,
                    response: Response<EmailVerificationResponse>
                ) {
                    isLoading.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    isSuccessful.value = true
                                }
                                ApiClient.STATUS_USER_EXIST->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_INVALID_TOKEN->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE->{
                                    errorApiLiveData.value = it.message
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<EmailVerificationResponse>, t: Throwable) {
                    isLoading.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}