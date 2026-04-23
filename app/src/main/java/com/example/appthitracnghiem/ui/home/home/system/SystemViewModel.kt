package com.example.appthitracnghiem.ui.home.home.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SystemViewModel : BaseViewModel() {
    var loadingData = MutableLiveData<Boolean>()
    var listDepartmentLiveData = MutableLiveData<MutableList<Department>>()
    val uiState = MutableLiveData<UiState<List<Department>>>(UiState.Idle)
    private val getDepartmentsUseCase = GetDepartmentsUseCase(HomeRepositoryImpl(ApiClient.shared()))

    fun getDataDepartment(accessToken: String,requestGetListDepartment: RequestGetListDepartment){
        loadingData.value = true
        uiState.value = UiState.Loading
        viewModelScope.launch {
            when (
                val result = getDepartmentsUseCase.getSystem(
                    accessToken = accessToken,
                    userId = requestGetListDepartment.user_id,
                    keyword = requestGetListDepartment.keyword,
                )
            ) {
                is ResultState.Error -> {
                    loadingData.value = false
                    uiState.value = UiState.Error(result.message)
                    errorApiLiveData.value = result.message
                }

                is ResultState.Success -> {
                    loadingData.value = false
                    listDepartmentLiveData.value = result.data.toMutableList()
                    uiState.value = UiState.Success(result.data)
                }
            }
        }
    }
}