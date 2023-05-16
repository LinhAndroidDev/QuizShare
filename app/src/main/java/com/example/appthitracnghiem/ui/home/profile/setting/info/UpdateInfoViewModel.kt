package com.example.appthitracnghiem.ui.home.profile.setting.info

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateInfoViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun updateInfo(
        header: String,
        requestUpdateInfo: RequestUpdateInfo
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().updateUserInfo(header, requestUpdateInfo)
            .enqueue(object : Callback<UpdateInfoResponse>{
                override fun onResponse(
                    call: Call<UpdateInfoResponse>,
                    response: Response<UpdateInfoResponse>
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

                override fun onFailure(call: Call<UpdateInfoResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}