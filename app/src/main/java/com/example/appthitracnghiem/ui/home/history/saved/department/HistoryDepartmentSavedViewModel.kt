package com.example.appthitracnghiem.ui.home.history.saved.department

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryDepartmentSavedViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listDepartmentSaved = MutableLiveData<ArrayList<Department>?>()

    fun getDepartmentSaved(requestSavedDepartment: RequestSavedDepartment) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.savedDepartment(requestSavedDepartment) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listDepartmentSaved.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
