package com.example.appthitracnghiem.ui.home.createtest.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateExamViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var uploadSuccessfulLiveData = MutableLiveData<Boolean>()

    fun createExam(requestCreateExam: RequestCreateExam) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.createExam(requestCreateExam) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }

    fun postUploadFile(
        user_id: RequestBody,
        file: MultipartBody.Part,
        folder_name: RequestBody,
        file_name: RequestBody,
    ) {
        viewModelScope.launch {
            val result = safeApiCall { apiService.postUploadFile(user_id, file, folder_name, file_name) }
            when (result) {
                is ResultState.Success -> uploadSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
