package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.domain.model.UserProfile
import com.example.appthitracnghiem.model.Department

interface HomeRepository {
    suspend fun getSystemDepartments(accessToken: String, userId: Int, keyword: String): ResultState<List<Department>>
    suspend fun getUserDepartments(accessToken: String, userId: Int, keyword: String): ResultState<List<Department>>
    suspend fun getUserProfile(accessToken: String, userId: Int): ResultState<UserProfile>
}
