package com.example.appthitracnghiem.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.GetUserProfileUseCase
import com.example.appthitracnghiem.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    var nameUserLiveData = MutableLiveData<String>()
    var avartarUserLiveData = MutableLiveData<String>()
    var isLoadingLiveData = MutableLiveData<Boolean>()
    val userUiState = MutableLiveData<UiState<Pair<String, String>>>(UiState.Idle)
    private val getUserProfileUseCase = GetUserProfileUseCase(HomeRepositoryImpl(ApiClient.shared()))

    fun getDataUserInfo(header: String, requestUserInfo: RequestUserInfo){
        isLoadingLiveData.value = true
        userUiState.value = UiState.Loading
        viewModelScope.launch {
            when (val result = getUserProfileUseCase(header, requestUserInfo.user_id)) {
                is ResultState.Error -> {
                    isLoadingLiveData.value = false
                    userUiState.value = UiState.Error(result.message)
                    errorApiLiveData.value = result.message
                }

                is ResultState.Success -> {
                    isLoadingLiveData.value = false
                    nameUserLiveData.value = result.data.name
                    avartarUserLiveData.value = result.data.avatar
                    userUiState.value = UiState.Success(result.data.name to result.data.avatar)
                }
            }
        }
    }
}