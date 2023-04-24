package com.example.appthitracnghiem.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.UserResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {
    var nameUserLiveData = MutableLiveData<String>()
    var avartarUserLiveData = MutableLiveData<String>()

    fun getDataUserInfo(header: String, requestUserInfo: RequestUserInfo){
        ApiClient.shared().getUserInfo(header, requestUserInfo)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    response.body()?.let {
                        if(it.statusCode == ApiClient.STATUS_CODE_SUCCESS){
                            nameUserLiveData.value = it.result?.name
                            avartarUserLiveData.value = it.result?.avatar
                        }
                        if(it.statusCode == ApiClient.STATUS_INVALID_TOKEN){
                            errorApiLiveData.value = it.message
                        }
                        if (it.statusCode == ApiClient.STATUS_USER_EXIST){
                            errorApiLiveData.value = it.message
                        }
                        if (it.statusCode == ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE){
                            errorApiLiveData.value = it.message
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    errorApiLiveData.value = t.message
                }

            })
    }
}