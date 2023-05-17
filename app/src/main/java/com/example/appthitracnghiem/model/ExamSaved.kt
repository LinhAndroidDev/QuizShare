package com.example.appthitracnghiem.model

data class ExamSaved(
    val author_email: String,
    val author_id: Int,
    val author_name: String,
    val id: Int,
    val number: Int,
    val saved_num: Int,
    val status: Int,
    val image: String?,
    val time: Int,
    val title: String
)