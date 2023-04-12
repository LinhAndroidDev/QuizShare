package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.Exam

data class ListExamResponse(
    override val result: Result?
) : BaseResponse<ListExamResponse.Result?>() {
    data class Result(
        val department_description: String,
        val department_id: Int,
        val department_title: String,
        val description: String,
        val id: Int,
        val list_exam: MutableList<Exam>,
        val title: String
    )
}