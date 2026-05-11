package com.example.appthitracnghiem.ui.exercise.exercise.exam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val listExamQuestionLiveData = MutableLiveData<ArrayList<ExamQuestion>>()
    val title = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getExamListQuestion(requestExamQuestion: RequestExamQuestion) {
        loadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamListQuestion(requestExamQuestion) }
            loadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listExamQuestionLiveData.value = result.data.result.exam_question_list
                is ResultState.Error -> {
                    Log.e("ExamViewModel", "Error fetching exam questions: ${result.message}")
                    errorApiLiveData.value = result.message
                }
            }
        }
    }
}
