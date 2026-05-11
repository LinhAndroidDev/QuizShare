package com.example.appthitracnghiem.ui.home.history.saved.subject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestSubjectSaved
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorySubjectSavedViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listSubjectSaved = MutableLiveData<ArrayList<SaveSubjectResponse.Result>?>()

    fun saveSubject(requestSubjectSaved: RequestSubjectSaved) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.savedSubject(requestSubjectSaved) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listSubjectSaved.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
