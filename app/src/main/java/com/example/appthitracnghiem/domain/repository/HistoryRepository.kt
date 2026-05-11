package com.example.appthitracnghiem.domain.repository

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamHistory
import com.example.appthitracnghiem.data.remote.dto.request.RequestSavedDepartment
import com.example.appthitracnghiem.data.remote.dto.request.RequestSubjectSaved
import com.example.appthitracnghiem.data.remote.dto.request.RequestTestSaved
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse

interface HistoryRepository {
    suspend fun getExamHistory(request: RequestExamHistory): ResultState<ExamHistoryResponse>
    suspend fun getExamHistoryDetail(userId: Int, examHistoryId: Int): ResultState<HistoryTopicResponse>
    suspend fun getSavedDepartments(request: RequestSavedDepartment): ResultState<DepartmentSavedResponse>
    suspend fun getSavedSubjects(request: RequestSubjectSaved): ResultState<SaveSubjectResponse>
    suspend fun getSavedTests(request: RequestTestSaved): ResultState<TestSavedResponse>
}
