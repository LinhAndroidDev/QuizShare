package com.example.appthitracnghiem.ui.home.home.system

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService,
) : BaseViewModel() {
    var loadingData = MutableLiveData<Boolean>()
    var listDepartmentLiveData = MutableLiveData<MutableList<Department>>()
    val uiState = MutableLiveData<UiState<List<Department>>>(UiState.Idle)
    private val getDepartmentsUseCase = GetDepartmentsUseCase(HomeRepositoryImpl(appContext, apiService))

    fun getDataDepartment(keyword: String) {
        loadingData.value = true
        uiState.value = UiState.Loading
        viewModelScope.launch {
            when (val result = getDepartmentsUseCase.getSystem(userId = 3, keyword = keyword)) {
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