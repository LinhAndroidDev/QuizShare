package com.example.appthitracnghiem.ui.login.forgetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var isSuccessful = MutableLiveData<Boolean>()

    fun checkEmail(requestEmailVerification: RequestEmailVerification) {
        isLoading.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.emailVerification(requestEmailVerification) }
            isLoading.value = false
            when (result) {
                is ResultState.Success -> isSuccessful.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
