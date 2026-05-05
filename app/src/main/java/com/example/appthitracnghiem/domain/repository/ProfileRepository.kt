package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse
import com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse

interface ProfileRepository {
    suspend fun updateEmail(request: Any): ResultState<UpdateEmailResponse>
    suspend fun updateInfo(request: Any): ResultState<UpdateInfoResponse>
    suspend fun changePassword(request: Any): ResultState<ChangePasswordResponse>
    suspend fun unPublishUser(request: Any): ResultState<UnPublishUserResponse>
}
