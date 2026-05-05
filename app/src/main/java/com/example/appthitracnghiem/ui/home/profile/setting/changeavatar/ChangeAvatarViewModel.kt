package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeAvatarViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var isLoadingLiveData = MutableLiveData<Boolean>()

    fun requestAvt(requestBodyId: RequestBody, requestBodyAvt: MultipartBody.Part) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.postImage(requestBodyId, requestBodyAvt) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
