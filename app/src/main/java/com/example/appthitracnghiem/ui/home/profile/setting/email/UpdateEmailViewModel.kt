package com.example.appthitracnghiem.ui.home.profile.setting.email

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UpdateEmailViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun updateEmail(
        header: String,
        requestUpdateEmail: RequestUpdateEmail
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().changeEmail(header, requestUpdateEmail)
            .enqueue(object : retrofit2.Callback<UpdateEmailResponse>{
                override fun onResponse(
                    call: Call<UpdateEmailResponse>,
                    response: Response<UpdateEmailResponse>
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    isSuccessfulLiveData.value = true
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

                override fun onFailure(call: Call<UpdateEmailResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}