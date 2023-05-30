package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class HistoryTopicResponse(
    override val result: Results
    ) : BaseResponse<HistoryTopicResponse.Results>() {
    data class Results(
        val id: Int?,
        val description: String?,
        val exam_title: String?,
        val time: Int?,
        val number: Int?,
        val subject_title: String?,
        val user_name: String?,
        val user_id: Int?,
        val user_avatar: String?,
    )
}