package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse

interface HistoryRepository {
    suspend fun getExamHistory(accessToken: String, request: Any): ResultState<ExamHistoryResponse>
    suspend fun getExamHistoryDetail(
        accessToken: String,
        userId: Int,
        examHistoryId: Int,
    ): ResultState<HistoryTopicResponse>

    suspend fun getSavedDepartments(accessToken: String, request: Any): ResultState<DepartmentSavedResponse>
    suspend fun getSavedSubjects(accessToken: String, request: Any): ResultState<SaveSubjectResponse>
    suspend fun getSavedTests(accessToken: String, request: Any): ResultState<TestSavedResponse>
}
