package com.example.appthitracnghiem.ui.department.listtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.Exam
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTestViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val listTestLiveData = MutableLiveData<MutableList<Exam>>()
    val loadingTestLiveData = MutableLiveData<Boolean>()

    fun getListExam(requestListExam: RequestListExam) {
        loadingTestLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getListExam(requestListExam) }
            loadingTestLiveData.value = false
            when (result) {
                is ResultState.Success -> listTestLiveData.value = result.data.result?.list_exam
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
