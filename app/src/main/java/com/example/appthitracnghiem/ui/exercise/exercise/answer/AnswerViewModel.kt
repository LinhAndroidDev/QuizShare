package com.example.appthitracnghiem.ui.exercise.exercise.answer

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnswerViewModel : BaseViewModel() {
    val listExamQuestionLiveData = MutableLiveData<ArrayList<ExamQuestion>>()
    val title = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val listAnswerLiveData = MutableLiveData<String>()

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
                            val t = body
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

    fun getExamResult(
        header: String,
        requestAnswer: RequestAnswer
    ){
        ApiClient.shared().getExamResult(header, requestAnswer)
            .enqueue(object : Callback<AnswerResponse>{
                override fun onResponse(
                    call: Call<AnswerResponse>,
                    response: Response<AnswerResponse>
                ) {
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    listAnswerLiveData.value = it.result?.exam_result
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

                override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                    errorApiLiveData.value = t.message
                }

            })
    }
}