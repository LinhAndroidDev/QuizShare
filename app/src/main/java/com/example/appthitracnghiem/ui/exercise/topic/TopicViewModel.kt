package com.example.appthitracnghiem.ui.exercise.topic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestSaveExam
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun saveExam(requestSaveExam: RequestSaveExam) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.saveExam(requestSaveExam) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
