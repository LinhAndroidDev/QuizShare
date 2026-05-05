package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.domain.model.UserProfile
import com.example.appthitracnghiem.model.Department

interface HomeRepository {
    suspend fun getSystemDepartments(userId: Int, keyword: String): ResultState<List<Department>>
    suspend fun getUserDepartments(userId: Int, keyword: String): ResultState<List<Department>>
    suspend fun getUserProfile(userId: Int): ResultState<UserProfile>
}
