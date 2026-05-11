package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestAnswer
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.dto.request.RequestPoint
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.data.remote.entity.PointResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.repository.ExamRepository
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ExamRepository {
    override suspend fun getExamQuestions(request: RequestExamQuestion): ResultState<ExamQuestionResponse> {
        return safeApiCall { apiService.getExamListQuestion(request) }
    }

    override suspend fun submitExam(request: RequestPoint): ResultState<PointResponse> {
        return safeApiCall { apiService.submitExam(request) }
    }

    override suspend fun getExamResult(request: RequestAnswer): ResultState<AnswerResponse> {
        return safeApiCall { apiService.getExamResult(request) }
    }
}
