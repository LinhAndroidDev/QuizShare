package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.ExamSaved

class TestSavedResponse(
    override val result: Result?
    ) : BaseResponse<TestSavedResponse.Result>() {
    data class Result(
        val department_id: Int,
        val department_title: String,
        val subject_title: String,
        val exam_list: ArrayList<ExamSaved>?
    )
}










