package com.example.appthitracnghiem.ui.exercise.exercise.point

data class RequestPoint(
    val user_id: Int,
    val exam_id: Int,
    val answer_list: HashMap<String,Int?>,
    val start_time: String,
    val finish_time: String
)