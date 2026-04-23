package com.example.appthitracnghiem.domain.usecase

import com.example.appthitracnghiem.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(loginId: String, password: String) = authRepository.login(loginId, password)
}
