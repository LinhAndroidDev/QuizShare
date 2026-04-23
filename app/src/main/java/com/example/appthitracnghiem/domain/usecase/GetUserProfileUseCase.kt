package com.example.appthitracnghiem.domain.usecase

import com.example.appthitracnghiem.domain.repository.HomeRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend operator fun invoke(accessToken: String, userId: Int) =
        homeRepository.getUserProfile(accessToken, userId)
}
