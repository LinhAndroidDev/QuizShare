package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.domain.model.AuthSession

interface AuthRepository {
    suspend fun login(loginId: String, password: String): ResultState<AuthSession>
    suspend fun register(
        email: String,
        name: String,
        phoneNumber: String,
        birthday: String,
        password: String,
    ): ResultState<AuthSession>
}
