package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.dto.request.RequestCreateExam
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CreateTestRepository {
    suspend fun createExam(request: RequestCreateExam): ResultState<CreateExamResponse>
    suspend fun uploadFile(
        userId: RequestBody,
        file: MultipartBody.Part,
        folderName: RequestBody,
        fileName: RequestBody,
    ): ResultState<UploadImageResponse>
}
