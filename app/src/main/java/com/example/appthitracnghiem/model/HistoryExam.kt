package com.example.appthitracnghiem.model

data class HistoryExam(
    val exam_history_id: Int?,
    val title: String?,
    val number: Int?,
    /** Creator display name or id as string from API (e.g. `"Admin"`). */
    val user_create: String?,
    val image: String?,
    val score: Float?
)