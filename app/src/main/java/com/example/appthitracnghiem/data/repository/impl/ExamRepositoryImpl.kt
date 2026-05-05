package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.data.remote.entity.PointResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.repository.ExamRepository
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ExamRepository {
    override suspend fun getExamQuestions(request: Any): ResultState<ExamQuestionResponse> {
        return safeApiCall { apiService.getExamListQuestion(request) }
    }

    override suspend fun submitExam(request: Any): ResultState<PointResponse> {
        return safeApiCall { apiService.submitExam(request) }
    }

    override suspend fun getExamResult(request: Any): ResultState<AnswerResponse> {
        return safeApiCall { apiService.getExamResult(request) }
    }
}
