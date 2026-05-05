package com.example.appthitracnghiem.data.repository.impl

import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HistoryRepository {
    override suspend fun getExamHistory(request: Any): ResultState<ExamHistoryResponse> {
        return safeApiCall { apiService.getExamHistory(request) }
    }

    override suspend fun getExamHistoryDetail(userId: Int, examHistoryId: Int): ResultState<HistoryTopicResponse> {
        return safeApiCall { apiService.getExamHistoryDetail(userId, examHistoryId) }
    }

    override suspend fun getSavedDepartments(request: Any): ResultState<DepartmentSavedResponse> {
        return safeApiCall { apiService.savedDepartment(request) }
    }

    override suspend fun getSavedSubjects(request: Any): ResultState<SaveSubjectResponse> {
        return safeApiCall { apiService.savedSubject(request) }
    }

    override suspend fun getSavedTests(request: Any): ResultState<TestSavedResponse> {
        return safeApiCall { apiService.saveTest(request) }
    }
}
