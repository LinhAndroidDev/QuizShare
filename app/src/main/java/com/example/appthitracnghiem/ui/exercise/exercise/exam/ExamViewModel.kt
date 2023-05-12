package com.example.appthitracnghiem.ui.exercise.exercise.exam

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExamViewModel : BaseViewModel() {
    val listExamQuestionLiveData = MutableLiveData<ArrayList<ExamQuestion>>()
    val title = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getExamListQuestion(header: String, requestExamQuestion: RequestExamQuestion){
        loadingLiveData.value = true
        ApiClient.shared().getExamListQuestion(header, requestExamQuestion)
            .enqueue(object : Callback<ExamQuestionResponse> {
                override fun onResponse(
                    call: Call<ExamQuestionResponse>,
                    response: Response<ExamQuestionResponse>
                ) {
                    loadingLiveData.value = false
                    response.body()?.let { body->
                        if(body.statusCode == ApiClient.STATUS_CODE_SUCCESS){
                            listExamQuestionLiveData.value = body.result.exam_question_list
                        }
                        if(body.statusCode == ApiClient.STATUS_INVALID_TOKEN){
                            errorApiLiveData.value = body.message
                        }
                        if(body.statusCode == ApiClient.STATUS_USER_EXIST){
                            errorApiLiveData.value = body.message
                        }
                        if (body.statusCode == ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE){
                            errorApiLiveData.value = body.message
                        }
                    }
                }

                override fun onFailure(call: Call<ExamQuestionResponse>, t: Throwable) {
                    loadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}