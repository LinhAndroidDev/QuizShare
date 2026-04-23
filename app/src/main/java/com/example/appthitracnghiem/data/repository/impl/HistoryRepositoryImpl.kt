package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse
import com.example.appthitracnghiem.data.remote.executeResultState
import com.example.appthitracnghiem.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HistoryRepository {
    override suspend fun getExamHistory(accessToken: String, request: Any): ResultState<ExamHistoryResponse> {
        return apiService.getExamHistory(accessToken, request).executeResultState()
    }

    override suspend fun getExamHistoryDetail(
        accessToken: String,
        userId: Int,
        examHistoryId: Int,
    ): ResultState<HistoryTopicResponse> {
        return apiService.getExamHistoryDetail(accessToken, userId, examHistoryId).executeResultState()
    }

    override suspend fun getSavedDepartments(
        accessToken: String,
        request: Any,
    ): ResultState<DepartmentSavedResponse> {
        return apiService.savedDepartment(accessToken, request).executeResultState()
    }

    override suspend fun getSavedSubjects(accessToken: String, request: Any): ResultState<SaveSubjectResponse> {
        return apiService.savedSubject(accessToken, request).executeResultState()
    }

    override suspend fun getSavedTests(accessToken: String, request: Any): ResultState<TestSavedResponse> {
        return apiService.saveTest(accessToken, request).executeResultState()
    }
}
