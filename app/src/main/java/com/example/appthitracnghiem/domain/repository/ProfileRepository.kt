package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.dto.request.RequestChangePassword
import com.example.appthitracnghiem.data.remote.dto.request.RequestUnPublishUser
import com.example.appthitracnghiem.data.remote.dto.request.RequestUpdateEmail
import com.example.appthitracnghiem.data.remote.dto.request.RequestUpdateInfo
import com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse
import com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse
import com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse

interface ProfileRepository {
    suspend fun updateEmail(request: RequestUpdateEmail): ResultState<UpdateEmailResponse>
    suspend fun updateInfo(request: RequestUpdateInfo): ResultState<UpdateInfoResponse>
    suspend fun changePassword(request: RequestChangePassword): ResultState<ChangePasswordResponse>
    suspend fun unPublishUser(request: RequestUnPublishUser): ResultState<UnPublishUserResponse>
}
