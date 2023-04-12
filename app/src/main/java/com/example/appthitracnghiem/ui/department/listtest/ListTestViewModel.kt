package com.example.appthitracnghiem.ui.department.listtest

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ListExamResponse
import com.example.appthitracnghiem.model.Exam
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTestViewModel : BaseViewModel() {
    val listTestLiveData = MutableLiveData<MutableList<Exam>>()

    fun getListExam(head: String, requestListExam: RequestListExam){
        ApiClient.shared().getListExam(head, requestListExam)
            .enqueue(object : Callback<ListExamResponse> {
                override fun onResponse(
                    call: Call<ListExamResponse>,
                    response: Response<ListExamResponse>
                ) {
                    response.body()?.let { body->
                        if(body.statusCode == ApiClient.STATUS_CODE_SUCCESS){
                            listTestLiveData.value = body.result?.list_exam
                        }
                        if(body.statusCode == ApiClient.STATUS_INVALID_TOKEN){
                            errorApiLiveData.value = body.message
                        }
                        if(body.statusCode == ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE){
                            errorApiLiveData.value = body.message
                        }
                    }
                }

                override fun onFailure(call: Call<ListExamResponse>, t: Throwable) {
                        errorApiLiveData.value = t.message
                }

            })
    }
}