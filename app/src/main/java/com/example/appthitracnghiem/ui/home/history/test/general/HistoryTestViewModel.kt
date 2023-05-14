package com.example.appthitracnghiem.ui.home.history.test.general

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryTestViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listExamHistoryLiveData = MutableLiveData<ArrayList<HistoryExam>>()

    fun getExamHistory(
        header: String,
        requestExamHistory: RequestExamHistory
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().getExamHistory(header, requestExamHistory)
            .enqueue(object : Callback<ExamHistoryResponse>{
                override fun onResponse(
                    call: Call<ExamHistoryResponse>,
                    response: Response<ExamHistoryResponse>,
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    listExamHistoryLiveData.value = it.result
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

                override fun onFailure(call: Call<ExamHistoryResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}