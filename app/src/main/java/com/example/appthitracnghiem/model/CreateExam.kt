package com.example.appthitracnghiem.model

data class CreateExam(
    val question_exam_list: ArrayList<CreateQuestion>,
    val user_id: Int,
    val subject_id: Int,
    val title: String,
    val time: Int,
    val number: Int,
    val status: Int
)