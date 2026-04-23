package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestGetListDepartment
import com.example.appthitracnghiem.data.remote.dto.request.RequestUserInfo
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.model.UserProfile
import com.example.appthitracnghiem.domain.repository.HomeRepository
import com.example.appthitracnghiem.model.Department
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {
    override suspend fun getSystemDepartments(
        accessToken: String,
        userId: Int,
        keyword: String,
    ): ResultState<List<Department>> {
        return getDepartments(accessToken, userId, keyword)
    }

    override suspend fun getUserDepartments(
        accessToken: String,
        userId: Int,
        keyword: String,
    ): ResultState<List<Department>> {
        return getDepartments(accessToken, userId, keyword)
    }

    private suspend fun getDepartments(
        accessToken: String,
        userId: Int,
        keyword: String,
    ): ResultState<List<Department>> {
        return when (
            val result = apiService.getDepartmentList(
                accessToken,
                RequestGetListDepartment(user_id = userId, keyword = keyword),
            ).executeResultState()
        ) {
            is ResultState.Error -> result
            is ResultState.Success -> ResultState.Success(result.data.result ?: emptyList())
        }
    }

    override suspend fun getUserProfile(accessToken: String, userId: Int): ResultState<UserProfile> {
        return when (val result = apiService.getUserInfo(accessToken, RequestUserInfo(userId)).executeResultState()) {
            is ResultState.Error -> result
            is ResultState.Success -> {
                val profile = result.data.result
                if (profile?.id == null || profile.name.isNullOrBlank()) {
                    ResultState.Error("User profile is invalid")
                } else {
                    ResultState.Success(
                        UserProfile(
                            id = profile.id,
                            name = profile.name,
                            avatar = profile.avatar.orEmpty(),
                        ),
                    )
                }
            }
        }
    }
}
