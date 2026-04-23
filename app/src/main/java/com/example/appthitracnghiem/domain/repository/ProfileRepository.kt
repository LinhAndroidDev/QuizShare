package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse
import com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse

interface ProfileRepository {
    suspend fun updateEmail(accessToken: String, request: Any): ResultState<UpdateEmailResponse>
    suspend fun updateInfo(accessToken: String, request: Any): ResultState<UpdateInfoResponse>
    suspend fun changePassword(accessToken: String, request: Any): ResultState<ChangePasswordResponse>
    suspend fun unPublishUser(accessToken: String, request: Any): ResultState<UnPublishUserResponse>
}
