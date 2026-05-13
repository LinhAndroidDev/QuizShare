package com.example.appthitracnghiem.ui.department.listdepartment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestDepartmentInfo
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDepartmentViewModel @Inject constructor(
    private val apiService: ApiService,
    private val getDepartmentsUseCase: GetDepartmentsUseCase,
) : BaseViewModel() {

    companion object {
        /** `user_id` mặc định khi gọi `getDepartmentList` trên màn tạo đề thi (popup chọn khoa). */
        const val CREATE_TEST_DEPARTMENT_LIST_USER_ID: Int = 3
    }

    val listDepartmentLiveData = MutableLiveData<MutableList<DetailDepartment>>()
    val loadingDepartmentLiveData = MutableLiveData<Boolean>()

    /** Danh sách khoa từ API `getDepartmentList` (dùng cho popup chọn khoa khi tạo đề). */
    val createTestDepartmentsLiveData = MutableLiveData<List<Department>>()

    fun fetchDepartmentListForCreateTest(
        userId: Int = CREATE_TEST_DEPARTMENT_LIST_USER_ID,
        keyword: String = "",
    ) {
        viewModelScope.launch {
            when (val result = getDepartmentsUseCase.getSystem(userId, keyword)) {
                is ResultState.Success -> createTestDepartmentsLiveData.value = result.data
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }

    fun getDataDepartmentDetail(requestDepartmentInfo: RequestDepartmentInfo) {
        loadingDepartmentLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getListDepartmentInfo(requestDepartmentInfo) }
            loadingDepartmentLiveData.value = false
            when (result) {
                is ResultState.Success -> listDepartmentLiveData.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
