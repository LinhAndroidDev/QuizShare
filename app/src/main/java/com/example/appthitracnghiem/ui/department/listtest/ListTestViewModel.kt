package com.example.appthitracnghiem.ui.department.listtest

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ListExamResponse
import com.example.appthitracnghiem.model.Exam
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTestViewModel : BaseViewModel() {
    val listTestLiveData = MutableLiveData<MutableList<Exam>>()
    val loadingTestLiveData = MutableLiveData<Boolean>()

    fun getListExam(head: String, requestListExam: RequestListExam) {
        loadingTestLiveData.value = true
        ApiClient.shared().getListExam(head, requestListExam)
            .enqueue(object : Callback<ListExamResponse> {
                override fun onResponse(
                    call: Call<ListExamResponse>,
                    response: Response<ListExamResponse>
                ) {
                    loadingTestLiveData.value = false
                    response.body()?.let { body ->
                        if (body.statusCode == ApiClient.STATUS_CODE_SUCCESS) {
                            listTestLiveData.value = body.result?.list_exam
                        }
                        if (body.statusCode == ApiClient.STATUS_INVALID_TOKEN) {
                            errorApiLiveData.value = body.message
                        }
                        if (body.statusCode == ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE) {
                            errorApiLiveData.value = body.message
                        }
                    }
                }

                override fun onFailure(call: Call<ListExamResponse>, t: Throwable) {
                    loadingTestLiveData.value = false
                    errorApiLiveData.value = t.message
                }
            })
    }
}