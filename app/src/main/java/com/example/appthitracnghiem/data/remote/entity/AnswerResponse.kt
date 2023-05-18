package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class AnswerResponse(
    override val result: Result?
    ) : BaseResponse<AnswerResponse.Result>() {
    data class Result(
        val correct_number: Int,
        val create_at: String,
        val delete_at: String?,
        val exam_id: Int,
        val exam_result: String,
        val finish_time: String,
        val id: Int,
        val score: Float,
        val start_time: String,
        val update_at: String,
        val user_id: Int,
        val wrong_number: Int
    )
}
