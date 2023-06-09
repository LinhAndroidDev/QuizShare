package com.example.appthitracnghiem.ui.home.createtest.review

import com.example.appthitracnghiem.model.CreateQuestion

data class RequestCreateExam(
    val question_exam_list: ArrayList<CreateQuestion?>,
    val user_id: Int,
    val subject_id: Int,
    val title: String,
    val time: Int,
    val number: Int,
    val status: Int
)