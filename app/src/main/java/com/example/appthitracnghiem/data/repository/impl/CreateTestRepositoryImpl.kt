package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.repository.CreateTestRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CreateTestRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CreateTestRepository {
    override suspend fun createExam(request: Any): ResultState<CreateExamResponse> {
        return safeApiCall { apiService.createExam(request) }
    }

    override suspend fun uploadFile(
        userId: RequestBody,
        file: MultipartBody.Part,
        folderName: RequestBody,
        fileName: RequestBody,
    ): ResultState<UploadImageResponse> {
        return safeApiCall { apiService.postUploadFile(userId, file, folderName, fileName) }
    }
}
