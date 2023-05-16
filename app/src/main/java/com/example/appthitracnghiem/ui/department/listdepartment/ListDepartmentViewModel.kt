package com.example.appthitracnghiem.ui.department.listdepartment

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.DepartmentResponse
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDepartmentViewModel : BaseViewModel() {
    val listDepartmentLiveData = MutableLiveData<MutableList<DetailDepartment>>()
    val loadingDepartmentLiveData = MutableLiveData<Boolean>()

    fun getDataDepartmentDetail(header: String, requestDepartmentInfo: RequestDepartmentInfo){
        loadingDepartmentLiveData.value = true
        ApiClient.shared().getListDepartmentInfo(header, requestDepartmentInfo)
            .enqueue(object : Callback<DepartmentResponse> {
                override fun onResponse(
                    call: Call<DepartmentResponse>,
                    response: Response<DepartmentResponse>,
                ) {
                    loadingDepartmentLiveData.value = false
                    response.body()?.let { body->
                        when (body.statusCode) {
                            ApiClient.STATUS_CODE_SUCCESS -> {
                                listDepartmentLiveData.value = body.result
                            }
                            ApiClient.STATUS_INVALID_TOKEN -> {
                                errorApiLiveData.value = body.message
                            }
                            else -> {
                                errorApiLiveData.value = body.message
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<DepartmentResponse>, t: Throwable) {
                    loadingDepartmentLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}