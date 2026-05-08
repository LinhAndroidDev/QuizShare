package com.example.appthitracnghiem.model

data class ExamQuestion(
    val answer_list: MutableList<Answer>,
    val question_id: Int,
    val question_image: String?,
    /** API returns strings such as `"EASY"`, `"MEDIUM"`, `"HARD"`. */
    val question_level: String?,
    val question_sort: Int,
    val question_title: String
    )