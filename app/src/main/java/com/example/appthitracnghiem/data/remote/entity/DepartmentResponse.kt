package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.DetailDepartment

class DepartmentResponse(
    override val result: MutableList<DetailDepartment>?
    ) : BaseResponse<MutableList<DetailDepartment>?>() {
}