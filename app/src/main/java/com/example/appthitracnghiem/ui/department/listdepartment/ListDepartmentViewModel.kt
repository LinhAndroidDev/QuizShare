package com.example.appthitracnghiem.ui.department.listdepartment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.data.remote.entity.DepartmentResponse
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDepartmentViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val listDepartmentLiveData = MutableLiveData<MutableList<DetailDepartment>>()
    val loadingDepartmentLiveData = MutableLiveData<Boolean>()

    fun getDataDepartmentDetail(requestDepartmentInfo: RequestDepartmentInfo) {
        loadingDepartmentLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getListDepartmentInfo(requestDepartmentInfo) }
            loadingDepartmentLiveData.value = false
            when (result) {
                is ResultState.Success -> listDepartmentLiveData.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
