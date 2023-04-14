package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.ExamQuestion

data class ExamQuestionResponse(
    override val result: Result
) : BaseResponse<ExamQuestionResponse.Result?>(){
    data class Result(
        val exam_question_list: MutableList<ExamQuestion>?,
        val id: Int
    )
}