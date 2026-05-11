package com.example.appthitracnghiem.ui.home.history.test.topic

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTopicViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService,
) : BaseViewModel() {
    val isLoadingLiveData = MutableLiveData<Boolean>()

    /** Emits real [com.example.appthitracnghiem.model.Exam.id] for loading questions via Bundle (not prefs). */
    val navigateToAnswerExamIdLiveData = MutableLiveData<Int>()

    fun getIdExam(userId: Int, examHistoryId: Int) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamHistoryDetail(userId, examHistoryId) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> {
                    val examId = result.data.result.exam_id
                    if (examId != null && examId > 0) {
                        navigateToAnswerExamIdLiveData.value = examId
                    } else {
                        errorApiLiveData.value = appContext.getString(R.string.error_exam_id_missing)
                    }
                }
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
