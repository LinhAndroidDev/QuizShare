package com.example.appthitracnghiem.model

data class CreateQuestion(
    val answer_list: ArrayList<CreateAnswer?>,
    val question_image: String?,
    val question_image_url: String?,
    val question_level: Int,
    val question_sort: Int,
    val question_title: String
)