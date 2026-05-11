package com.example.appthitracnghiem.ui.exercise.exercise.answer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestAnswer
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswerViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val listExamQuestionLiveData = MutableLiveData<ArrayList<ExamQuestion>?>()
    val title = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val listAnswerLiveData = MutableLiveData<Map<String, Int?>?>()

    fun getExamListQuestion(requestExamQuestion: RequestExamQuestion) {
        loadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamListQuestion(requestExamQuestion) }
            loadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listExamQuestionLiveData.value = result.data.result.exam_question_list
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }

    fun getExamResult(requestAnswer: RequestAnswer) {
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamResult(requestAnswer) }
            when (result) {
                is ResultState.Success -> {
                    listAnswerLiveData.value = result.data.result?.exam_result
                }
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
