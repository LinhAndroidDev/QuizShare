package com.example.appthitracnghiem.ui.home.profile.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestUnPublishUser
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val apiService: ApiService,
    private val sessionLocalDataSource: SessionLocalDataSource,
) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun confirmLoggedOut() {
        sessionLocalDataSource.logout()
    }

    fun unPublishUser(requestUnPublishUser: RequestUnPublishUser) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.unPublishUser(requestUnPublishUser) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
