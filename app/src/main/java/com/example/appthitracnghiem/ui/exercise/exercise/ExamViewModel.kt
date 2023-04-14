package com.example.appthitracnghiem.ui.exercise.exercise

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExamViewModel : BaseViewModel() {
    val listExamQuestionLiveData = MutableLiveData<MutableList<ExamQuestion>>()
    val title = MutableLiveData<String>()
    val answer1 = MutableLiveData<String>()
    val answer2 = MutableLiveData<String>()
    val answer3 = MutableLiveData<String>()
    val answer4 = MutableLiveData<String>()

    fun getExamListQuestion(header: String, requestExamQuestion: RequestExamQuestion){
        ApiClient.shared().getExamListQuestion(header, requestExamQuestion)
            .enqueue(object : Callback<ExamQuestionResponse> {
                override fun onResponse(
                    call: Call<ExamQuestionResponse>,
                    response: Response<ExamQuestionResponse>
                ) {
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
                    errorApiLiveData.value = t.message
                }

            })
    }
}