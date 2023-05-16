package com.example.appthitracnghiem.ui.home.history.saved.department

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryDepartmentSavedViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listDepartmentSaved = MutableLiveData<ArrayList<Department>>()

    fun getDepartmentSaved(
        header: String,
        requestSavedDepartment: RequestSavedDepartment)
    {
        isLoadingLiveData.value = true
        ApiClient.shared().savedDepartment(header, requestSavedDepartment)
            .enqueue(object : Callback<DepartmentSavedResponse>{
                override fun onResponse(
                    call: Call<DepartmentSavedResponse>,
                    response: Response<DepartmentSavedResponse>
                ) {
                    isLoadingLiveData.value = false
                    response.body().let {
                        when(it?.statusCode){
                            ApiClient.STATUS_CODE_SUCCESS->{
                                listDepartmentSaved.value = it.result
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

                override fun onFailure(call: Call<DepartmentSavedResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                }

            })
    }
}