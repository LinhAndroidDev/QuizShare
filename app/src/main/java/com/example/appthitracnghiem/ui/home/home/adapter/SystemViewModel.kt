package com.example.appthitracnghiem.ui.home.home.adapter

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.FromSystemResponse
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class SystemViewModel : BaseViewModel() {
    val loadingData = MutableLiveData<Boolean>()
    val listDepartmentLiveData = MutableLiveData<MutableList<Department>>()

    init {
        getDataDepartment()
    }

    fun getDataDepartment(){
        loadingData.value = true
        ApiClient.shared().getDepartmentList().enqueue(object : retrofit2.Callback<FromSystemResponse> {
            override fun onResponse(
                call: Call<FromSystemResponse>,
                response: Response<FromSystemResponse>,
            ) {
                loadingData.value = false
                listDepartmentLiveData.value = response.body().result
            }

            override fun onFailure(call: Call<FromSystemResponse>, t: Throwable) {
                loadingData.value = false
            }

        })
    }
}