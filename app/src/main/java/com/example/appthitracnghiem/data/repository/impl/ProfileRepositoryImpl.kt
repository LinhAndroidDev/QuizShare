package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse
import com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProfileRepository {
    override suspend fun updateEmail(request: Any): ResultState<UpdateEmailResponse> {
        return safeApiCall { apiService.changeEmail(request) }
    }

    override suspend fun updateInfo(request: Any): ResultState<UpdateInfoResponse> {
        return safeApiCall { apiService.updateUserInfo(request) }
    }

    override suspend fun changePassword(request: Any): ResultState<ChangePasswordResponse> {
        return safeApiCall { apiService.changePassword(request) }
    }

    override suspend fun unPublishUser(request: Any): ResultState<UnPublishUserResponse> {
        return safeApiCall { apiService.unPublishUser(request) }
    }
}
