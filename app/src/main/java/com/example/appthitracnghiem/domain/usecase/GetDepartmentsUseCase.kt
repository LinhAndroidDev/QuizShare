package com.example.appthitracnghiem.domain.usecase

import com.example.appthitracnghiem.domain.repository.HomeRepository
import javax.inject.Inject

class GetDepartmentsUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun getSystem(userId: Int, keyword: String) =
        homeRepository.getSystemDepartments(userId, keyword)

    suspend fun getFromUser(userId: Int, keyword: String) =
        homeRepository.getUserDepartments(userId, keyword)
}
