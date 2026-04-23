package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.data.remote.entity.PointResponse
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.repository.ExamRepository
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ExamRepository {
    override suspend fun getExamQuestions(accessToken: String, request: Any): ResultState<ExamQuestionResponse> {
        return apiService.getExamListQuestion(accessToken, request).executeResultState()
    }

    override suspend fun submitExam(accessToken: String, request: Any): ResultState<PointResponse> {
        return apiService.submitExam(accessToken, request).executeResultState()
    }

    override suspend fun getExamResult(accessToken: String, request: Any): ResultState<AnswerResponse> {
        return apiService.getExamResult(accessToken, request).executeResultState()
    }
}
