package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse

interface HistoryRepository {
    suspend fun getExamHistory(request: Any): ResultState<ExamHistoryResponse>
    suspend fun getExamHistoryDetail(userId: Int, examHistoryId: Int): ResultState<HistoryTopicResponse>
    suspend fun getSavedDepartments(request: Any): ResultState<DepartmentSavedResponse>
    suspend fun getSavedSubjects(request: Any): ResultState<SaveSubjectResponse>
    suspend fun getSavedTests(request: Any): ResultState<TestSavedResponse>
}
