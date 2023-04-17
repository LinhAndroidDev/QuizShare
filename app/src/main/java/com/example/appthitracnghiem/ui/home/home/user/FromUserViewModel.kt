package com.example.appthitracnghiem.ui.home.home.user

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.FromSystemResponse
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import retrofit2.Call
import retrofit2.Response

class FromUserViewModel : BaseViewModel() {
    var loadingFromUserData = MutableLiveData<Boolean>()
    var listDepartmentFromUserLiveData = MutableLiveData<MutableList<Department>>()

    fun getDataDepartmentFromUser(accessToken: String,requestGetListDepartment: RequestGetListDepartment){
        loadingFromUserData.value = true
        ApiClient.shared().getDepartmentList(accessToken,requestGetListDepartment).enqueue(object : retrofit2.Callback<FromSystemResponse> {
            override fun onResponse(
                call: Call<FromSystemResponse>,
                response: Response<FromSystemResponse>,
            ) {
                loadingFromUserData.value = false
                listDepartmentFromUserLiveData.value = response.body()?.result as MutableList<Department>
            }

            override fun onFailure(call: Call<FromSystemResponse>, t: Throwable) {
                loadingFromUserData.value = false
                errorApiLiveData.value = t.message
            }

        })
    }
}