package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class SaveExamResponse(
    override val result: Result?
    ) : BaseResponse<SaveExamResponse.Result?>() {
    data class Result(
        val create_at: String?,
        val exam_id: Int?,
        val user_id: Int?
    )
}