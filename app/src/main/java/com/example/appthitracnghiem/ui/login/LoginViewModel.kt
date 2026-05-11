package com.example.appthitracnghiem.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl
import com.example.appthitracnghiem.domain.usecase.LoginUseCase
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.Email
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService,
    private val sessionLocalDataSource: SessionLocalDataSource,
) : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val successLoginLiveData = MutableLiveData<Boolean>()
    val validateLiveData = MutableLiveData<ValidateModel>()
    val loginUiState = MutableLiveData<UiState<Boolean>>(UiState.Idle)
    private val loginUseCase = LoginUseCase(AuthRepositoryImpl(appContext, apiService))

    private fun validateLogin(strEmail: String, strPassword: String): ValidateModel {
        return if (strEmail.isEmpty() || strPassword.isEmpty()) {
            ValidateModel(false, R.string.txt_notification_login, R.color.color_green)
        } else {
            val email = Email(strEmail, strPassword)
            if (email.isValidEmail() || email.isValidPhone()) {
                if (!email.isPassword()) {
                    ValidateModel(false, R.string.txt_warning_password, R.color.color_red)
                } else {
                    ValidateModel(true, -1, -1)
                }
            } else {
                ValidateModel(false, R.string.txt_warning_login, R.color.color_red)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun login(strEmail: String, strPassword: String) {
        val validateModel = validateLogin(strEmail, strPassword)
        validateLiveData.value = validateModel
        if (validateModel.isValidate) {
            requestLogin(strEmail, strPassword)
        }
    }

    private fun requestLogin(strEmail: String, strPassword: String) {
        loadingLiveData.value = true
        loginUiState.value = UiState.Loading
        viewModelScope.launch {
            when (val result = loginUseCase(strEmail, strPassword)) {
                is ResultState.Error -> {
                    loadingLiveData.value = false
                    loginUiState.value = UiState.Error(result.message)
                    errorApiLiveData.value = result.message
                }

                is ResultState.Success -> {
                    loadingLiveData.value = false
                    sessionLocalDataSource.saveUserSession(result.data.accessToken, result.data.userId)
                    successLoginLiveData.value = true
                    loginUiState.value = UiState.Success(true)
                }
            }
        }
    }
}
