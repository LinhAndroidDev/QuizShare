package com.example.appthitracnghiem.ui.home.home.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import kotlinx.coroutines.launch

class FromUserViewModel : BaseViewModel() {
    var loadingFromUserData = MutableLiveData<Boolean>()
    var listDepartmentFromUserLiveData = MutableLiveData<MutableList<Department>>()
    val uiState = MutableLiveData<UiState<List<Department>>>(UiState.Idle)
    private val getDepartmentsUseCase = GetDepartmentsUseCase(HomeRepositoryImpl(ApiClient.shared()))

    fun getDataDepartmentFromUser(accessToken: String,requestGetListDepartment: RequestGetListDepartment){
        loadingFromUserData.value = true
        uiState.value = UiState.Loading
        viewModelScope.launch {
            when (
                val result = getDepartmentsUseCase.getFromUser(
                    accessToken = accessToken,
                    userId = requestGetListDepartment.user_id,
                    keyword = requestGetListDepartment.keyword,
                )
            ) {
                is ResultState.Error -> {
                    loadingFromUserData.value = false
                    uiState.value = UiState.Error(result.message)
                    errorApiLiveData.value = result.message
                }

                is ResultState.Success -> {
                    loadingFromUserData.value = false
                    listDepartmentFromUserLiveData.value = result.data.toMutableList()
                    uiState.value = UiState.Success(result.data)
                }
            }
        }
    }
}