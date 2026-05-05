package com.example.appthitracnghiem.ui.home.history.test.topic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTopicViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun getIdExam(userId: Int, examHistoryId: Int) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamHistoryDetail(userId, examHistoryId) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> {
                    result.data.result.id?.let { id ->
                        mPreferenceUtil.defaultPref().edit()
                            .putInt(PreferenceKey.ID_EXAM, id)
                            .apply()
                    }
                    isSuccessfulLiveData.value = true
                }
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
