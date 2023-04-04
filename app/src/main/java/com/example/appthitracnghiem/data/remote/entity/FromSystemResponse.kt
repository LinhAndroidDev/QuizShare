package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.Department

data class FromSystemResponse(
    override val result: Department?
    ) : BaseResponse<Department>()