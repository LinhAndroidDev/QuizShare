package com.example.appthitracnghiem.ui.home.profile.setting.password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestChangePassword
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun changePassword(requestChangePassword: RequestChangePassword) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.changePassword(requestChangePassword) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
