package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ChangeAvatarResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeAvatarViewModel : BaseViewModel() {
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var isLoadingLiveData = MutableLiveData<Boolean>()

    fun requestAvt(
        header: String,
        requestBodyId: RequestBody,
        requestBodyAvt: MultipartBody.Part
    )
    {
        isLoadingLiveData.value = true
        ApiClient.shared().postImage(header, requestBodyId, requestBodyAvt)
            .enqueue(object : Callback<ChangeAvatarResponse>{
                override fun onResponse(
                    call: Call<ChangeAvatarResponse>,
                    response: Response<ChangeAvatarResponse>,
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body()?.let {
                            when(it.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS-> {
                                    isSuccessfulLiveData.value= true
                                }
                                ApiClient.STATUS_USER_EXIST -> {
                                    errorApiLiveData.value  = it.message
                                }
                                ApiClient.STATUS_INVALID_TOKEN -> {
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE -> {
                                    errorApiLiveData.value = it.message
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ChangeAvatarResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}