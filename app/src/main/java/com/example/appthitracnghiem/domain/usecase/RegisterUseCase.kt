package com.example.appthitracnghiem.domain.usecase

import com.example.appthitracnghiem.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        phoneNumber: String,
        birthday: String,
        password: String,
    ) = authRepository.register(email, name, phoneNumber, birthday, password)
}
