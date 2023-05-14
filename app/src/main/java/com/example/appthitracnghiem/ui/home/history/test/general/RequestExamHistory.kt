package com.example.appthitracnghiem.ui.home.history.test.general

data class RequestExamHistory(
    val user_id: Int,
    val limit: Int,
    val offset: Int,
    val sort_field: Int,
    val sort_by: String
)