package com.example.appthitracnghiem.ui.home.history.saved.test

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse
import com.example.appthitracnghiem.model.ExamSaved
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestSavedViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var departmentTitleLiveData = MutableLiveData<String>()
    var testTitleLiveData = MutableLiveData<String>()
    var listTestSavedLiveData = MutableLiveData<ArrayList<ExamSaved>?>()

    fun savedTest(
        header: String,
        requestTestSaved: RequestTestSaved
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().saveTest(header, requestTestSaved)
            .enqueue(object : Callback<TestSavedResponse>{
                override fun onResponse(
                    call: Call<TestSavedResponse>,
                    response: Response<TestSavedResponse>
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    departmentTitleLiveData.value = it.result?.department_title
                                    testTitleLiveData.value = it.result?.subject_title
                                    listTestSavedLiveData.value = it.result?.listExamSaved
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

                override fun onFailure(call: Call<TestSavedResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}