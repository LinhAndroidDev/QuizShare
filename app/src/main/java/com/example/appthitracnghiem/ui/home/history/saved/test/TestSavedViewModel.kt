package com.example.appthitracnghiem.ui.home.history.saved.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.ExamSaved
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestSavedViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var departmentTitleLiveData = MutableLiveData<String>()
    var testTitleLiveData = MutableLiveData<String>()
    var listTestSavedLiveData = MutableLiveData<ArrayList<ExamSaved>?>()

    fun savedTest(requestTestSaved: RequestTestSaved) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.saveTest(requestTestSaved) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> {
                    departmentTitleLiveData.value = result.data.result?.department_title
                    testTitleLiveData.value = result.data.result?.subject_title
                    listTestSavedLiveData.value = result.data.result?.exam_list
                }
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
