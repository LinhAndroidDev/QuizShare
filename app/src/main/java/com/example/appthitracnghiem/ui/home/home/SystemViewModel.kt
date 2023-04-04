package com.example.appthitracnghiem.ui.home.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.FromSystemResponse
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class SystemViewModel : BaseViewModel() {
    var loadingData = MutableLiveData<Boolean>()
    var listDepartmentLiveData = MutableLiveData<MutableList<Department>>()

    fun getDataDepartment(accessToken: String,requestGetListDepartment: RequestGetListDepartment){
        loadingData.value = true
        ApiClient.shared().getDepartmentList(accessToken,requestGetListDepartment).enqueue(object : retrofit2.Callback<FromSystemResponse> {
            override fun onResponse(
                call: Call<FromSystemResponse>,
                response: Response<FromSystemResponse>,
            ) {
                Log.e("TAG", Gson().toJson(response.body()?.result))
                loadingData.value = false
                listDepartmentLiveData.value = response.body()?.result as MutableList<Department>
            }

            override fun onFailure(call: Call<FromSystemResponse>, t: Throwable) {
                loadingData.value = false
                errorApiLiveData.value = t.message
            }

        })
    }
}