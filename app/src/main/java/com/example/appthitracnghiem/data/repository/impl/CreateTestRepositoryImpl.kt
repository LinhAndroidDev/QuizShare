package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.repository.CreateTestRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CreateTestRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CreateTestRepository {
    override suspend fun createExam(accessToken: String, request: Any): ResultState<CreateExamResponse> {
        return apiService.createExam(accessToken, request).executeResultState()
    }

    override suspend fun uploadFile(
        accessToken: String,
        userId: RequestBody,
        file: MultipartBody.Part,
        folderName: RequestBody,
        fileName: RequestBody,
    ): ResultState<UploadImageResponse> {
        return apiService.postUploadFile(accessToken, userId, file, folderName, fileName).executeResultState()
    }
}
