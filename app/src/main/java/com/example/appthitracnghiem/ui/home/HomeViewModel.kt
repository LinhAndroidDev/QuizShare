package com.example.appthitracnghiem.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.GetUserProfileUseCase
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService,
) : BaseViewModel() {
    var nameUserLiveData = MutableLiveData<String>()
    var avartarUserLiveData = MutableLiveData<String>()
    var isLoadingLiveData = MutableLiveData<Boolean>()
    val userUiState = MutableLiveData<UiState<Pair<String, String>>>(UiState.Idle)
    private val getUserProfileUseCase = GetUserProfileUseCase(HomeRepositoryImpl(appContext, apiService))

    fun getDataUserInfo(userId: Int) {
        isLoadingLiveData.value = true
        userUiState.value = UiState.Loading
        viewModelScope.launch {
            when (val result = getUserProfileUseCase(userId)) {
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
