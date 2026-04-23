package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestLogin
import com.example.appthitracnghiem.data.remote.dto.request.RequestRegister
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.model.AuthSession
import com.example.appthitracnghiem.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {
    override suspend fun login(loginId: String, password: String): ResultState<AuthSession> {
        return when (val result = apiService.loginUser(RequestLogin(loginId, password)).executeResultState()) {
            is ResultState.Error -> result
            is ResultState.Success -> {
                val payload = result.data.result
                if (payload?.user_id == null || payload.access_token.isNullOrBlank()) {
                    ResultState.Error("Login response is invalid")
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
            val result = apiService.registerUser(
                RequestRegister(
                    email = email,
                    name = name,
                    phone_number = phoneNumber,
                    birthday = birthday,
                    password = password,
                ),
            ).executeResultState()
        ) {
            is ResultState.Error -> result
            is ResultState.Success -> {
                val payload = result.data.result
                if (payload?.user_id == null || payload.access_token.isNullOrBlank()) {
                    ResultState.Error("Register response is invalid")
                } else {
                    ResultState.Success(AuthSession(payload.user_id, payload.access_token))
                }
            }
        }
    }
}
