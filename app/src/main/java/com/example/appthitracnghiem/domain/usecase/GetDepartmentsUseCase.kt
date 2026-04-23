package com.example.appthitracnghiem.domain.usecase

import com.example.appthitracnghiem.domain.repository.HomeRepository
import javax.inject.Inject

class GetDepartmentsUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun getSystem(accessToken: String, userId: Int, keyword: String) =
        homeRepository.getSystemDepartments(accessToken, userId, keyword)

    suspend fun getFromUser(accessToken: String, userId: Int, keyword: String) =
        homeRepository.getUserDepartments(accessToken, userId, keyword)
}
