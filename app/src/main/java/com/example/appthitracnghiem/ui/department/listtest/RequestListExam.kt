package com.example.appthitracnghiem.ui.department.listtest

data class RequestListExam(
    val user_id: Int,
    val subject_id: Int,
    val type: Int,
    val sort_field: Int,
    val sort_by: String
    )