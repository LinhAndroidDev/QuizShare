package com.example.appthitracnghiem.ui.home.home.system

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.FromSystemResponse
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response

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