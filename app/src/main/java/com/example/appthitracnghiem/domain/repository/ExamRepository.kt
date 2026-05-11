package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.dto.request.RequestAnswer
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.dto.request.RequestPoint
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.data.remote.entity.PointResponse

interface ExamRepository {
    suspend fun getExamQuestions(request: RequestExamQuestion): ResultState<ExamQuestionResponse>
    suspend fun submitExam(request: RequestPoint): ResultState<PointResponse>
    suspend fun getExamResult(request: RequestAnswer): ResultState<AnswerResponse>
}
