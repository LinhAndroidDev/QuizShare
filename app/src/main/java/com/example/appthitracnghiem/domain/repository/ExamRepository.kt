package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse
import com.example.appthitracnghiem.data.remote.entity.PointResponse

interface ExamRepository {
    suspend fun getExamQuestions(request: Any): ResultState<ExamQuestionResponse>
    suspend fun submitExam(request: Any): ResultState<PointResponse>
    suspend fun getExamResult(request: Any): ResultState<AnswerResponse>
}
