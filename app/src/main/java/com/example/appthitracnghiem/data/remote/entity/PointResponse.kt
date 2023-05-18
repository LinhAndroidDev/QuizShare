package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class PointResponse(
    override val result: Result?
): BaseResponse<PointResponse.Result?>() {
    data class Result(
        val correct_number: Int?,
        val create_at: String?,
        val exam_history_id: Int?,
        val exam_id: Int?,
        val exam_result: HashMap<String,Int?>,
        val finish_time: String?,
        val score: Float?,
        val skip_number: Int?,
        val start_time: String?,
        val update_at: String?,
        val user_id: Int?,
        val wrong_number: Int?
    )
}