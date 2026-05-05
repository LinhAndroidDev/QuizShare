package com.example.appthitracnghiem.ui.exercise.exercise.point

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var scoreLiveData = MutableLiveData<Float>()
    var numberCorrectLiveData = MutableLiveData<Int>()
    var skipNumberLiveData = MutableLiveData<Int>()
    var wrongNumberLiveData = MutableLiveData<Int>()
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var examIdHistory = MutableLiveData<Int>()

    fun getResult(requestPoint: RequestPoint) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.submitExam(requestPoint) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> {
                    scoreLiveData.value = result.data.result?.score
                    numberCorrectLiveData.value = result.data.result?.correct_number
                    skipNumberLiveData.value = result.data.result?.skip_number
                    wrongNumberLiveData.value = result.data.result?.wrong_number
                    examIdHistory.value = result.data.result?.exam_history_id
                }
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
