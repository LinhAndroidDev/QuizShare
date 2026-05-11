package com.example.appthitracnghiem.data.repository.impl

import android.content.Context
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestLogin
import com.example.appthitracnghiem.data.remote.dto.request.RequestRegister
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.model.AuthSession
import com.example.appthitracnghiem.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
) : AuthRepository {
    override suspend fun login(loginId: String, password: String): ResultState<AuthSession> {
        return when (val result = safeApiCall { apiService.loginUser(RequestLogin(loginId, password)) }) {
            is ResultState.Error -> result
            is ResultState.Success -> {
                val payload = result.data.result
                if (payload?.user_id == null || payload.access_token.isNullOrBlank()) {
                    ResultState.Error(context.getString(R.string.error_login_response_invalid))
                } else {
                    ResultState.Success(AuthSession(payload.user_id, payload.access_token))
                }
            }
        }
    }

    override suspend fun register(
        email: String,
        name: String,
        phoneNumber: String,
        birthday: String,
        password: String,
    ): ResultState<AuthSession> {
        return when (
            val result = safeApiCall {
                apiService.registerUser(
                    RequestRegister(
                        email = email,
                        name = name,
                        phone_number = phoneNumber,
                        birthday = birthday,
                        password = password,
                    ),
                )
            }
        ) {
            is ResultState.Error -> result
            is ResultState.Success -> {
                val payload = result.data.result
                if (payload?.user_id == null || payload.access_token.isNullOrBlank()) {
                    ResultState.Error(context.getString(R.string.error_register_response_invalid))
                } else {
                    ResultState.Success(AuthSession(payload.user_id, payload.access_token))
                }
            }
        }
    }
}
