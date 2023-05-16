package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.Department

class DepartmentSavedResponse(
    override val result: ArrayList<Department>?
    ) : BaseResponse<ArrayList<Department>?>()