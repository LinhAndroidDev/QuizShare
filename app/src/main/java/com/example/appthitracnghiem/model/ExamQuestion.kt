package com.example.appthitracnghiem.model

data class ExamQuestion(
    val answer_list: MutableList<Answer>,
    val question_id: Int,
    val question_image: String?,
    val question_level: Int,
    val question_sort: Int,
    val question_title: String
    )