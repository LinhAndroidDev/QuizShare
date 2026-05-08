package com.example.appthitracnghiem.ui.home.history.test.general

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTestViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listExamHistoryLiveData = MutableLiveData<ArrayList<HistoryExam>?>()
    var idExamHistoryLiveData = MutableLiveData<Int>()

    fun getExamHistory(requestExamHistory: RequestExamHistory) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamHistory(requestExamHistory) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listExamHistoryLiveData.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
