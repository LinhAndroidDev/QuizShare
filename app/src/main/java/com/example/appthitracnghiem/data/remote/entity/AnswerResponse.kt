package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class AnswerResponse(
    override val result: Result?,
) : BaseResponse<AnswerResponse.Result?>() {
    data class Result(
        val correct_number: Int,
        val create_at: String?,
        val delete_at: String?,
        val exam_id: Int,
        /** Map of question_id (as string key) to chosen answer_id, e.g. `{"1": 3, "2": 6}`. */
        val exam_result: HashMap<String, Int?>?,
        val finish_time: String?,
        val id: Int,
        val score: Float,
        val skip_number: Int?,
        val start_time: String?,
        val update_at: String?,
        val user_id: Int,
        val wrong_number: Int,
    )
}
