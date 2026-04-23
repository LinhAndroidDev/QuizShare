package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse
import com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProfileRepository {
    override suspend fun updateEmail(accessToken: String, request: Any): ResultState<UpdateEmailResponse> {
        return apiService.changeEmail(accessToken, request).executeResultState()
    }

    override suspend fun updateInfo(accessToken: String, request: Any): ResultState<UpdateInfoResponse> {
        return apiService.updateUserInfo(accessToken, request).executeResultState()
    }

    override suspend fun changePassword(accessToken: String, request: Any): ResultState<ChangePasswordResponse> {
        return apiService.changePassword(accessToken, request).executeResultState()
    }

    override suspend fun unPublishUser(accessToken: String, request: Any): ResultState<UnPublishUserResponse> {
        return apiService.unPublishUser(accessToken, request).executeResultState()
    }
}
