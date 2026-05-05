package com.example.appthitracnghiem.ui.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.core.UiState
import com.example.appthitracnghiem.domain.usecase.RegisterUseCase
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.login.ValidateModel
import com.example.appthitracnghiem.utils.Email
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@HiltViewModel
class RegisterViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val successRegisterLiveData = MutableLiveData<Boolean>()
    val validateLiveData = MutableLiveData<ValidateModel>()
    val registerUiState = MutableLiveData<UiState<Boolean>>(UiState.Idle)
    private val registerUseCase = RegisterUseCase(AuthRepositoryImpl(apiService))

    private fun validateRegister(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String,
        strPasswordRepeat: String,
    ): ValidateModel {
        return if (strName.isEmpty() || strYearOfBirth.isEmpty() || strEmail.isEmpty() || strPhone.isEmpty() || strPassword.isEmpty() || strPasswordRepeat.isEmpty()) {
            ValidateModel(false, R.string.txt_notification_register, R.color.color_green)
        } else {
            val email = Email(strEmail, strPassword)
            if (!email.isValidEmail()) {
                ValidateModel(false, R.string.txt_warning_login, R.color.color_red)
            } else {
                if (!Patterns.PHONE.matcher(strPhone)
                        .matches() || strPhone.length < 10
                ) {
                    ValidateModel(false, R.string.txt_warning_phone, R.color.color_red)
                } else {
                    if (!email.isPassword()) {
                        ValidateModel(false, R.string.txt_warning_password, R.color.color_red)
                    } else {
                        if (strPassword != strPasswordRepeat) {
                            ValidateModel(false, R.string.txtEnterRepeatPassword, R.color.color_red)
                        } else {
                            ValidateModel(true, -1, -1)
                        }
                    }
                }
            }
        }
    }

    fun register(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String,
        strPasswordRepeat: String,
    ) {
        val validateModel = validateRegister(
            strEmail,
            strName,
            strPhone,
            strYearOfBirth,
            strPassword,
            strPasswordRepeat
        )
        validateLiveData.value = validateModel
        if (validateModel.isValidate) {
            requestRegister(strEmail, strName, strPhone, strYearOfBirth, strPassword)
        }
    }

    private fun requestRegister(
        strEmail: String,
        strName: String,
        strPhone: String,
        strYearOfBirth: String,
        strPassword: String,
    ) {
        loadingLiveData.value = true
        registerUiState.value = UiState.Loading
        viewModelScope.launch {
            when (
                val result = registerUseCase(
                    email = strEmail,
                    name = strName,
                    phoneNumber = strPhone,
                    birthday = strYearOfBirth,
                    password = strPassword,
                )
            ) {
                is ResultState.Error -> {
                    loadingLiveData.value = false
                    registerUiState.value = UiState.Error(result.message)
                    errorApiLiveData.value = result.message
                }

                is ResultState.Success -> {
                    loadingLiveData.value = false
                    successRegisterLiveData.value = true
                    registerUiState.value = UiState.Success(true)
                }
            }
        }
    }
}
