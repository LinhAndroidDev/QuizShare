package com.example.appthitracnghiem.model.examui

/** Một đáp án trên màn làm bài (dữ liệu cho RecyclerView đáp án). */
data class ExamTakingAnswerOption(
    val answerId: Int,
    val content: String,
    val isCorrect: Boolean,
)
