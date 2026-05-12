package com.example.appthitracnghiem.model.examui

import androidx.annotation.DrawableRes

/** Một dòng đáp án trên màn xem lại (đã tính sẵn drawable). */
data class ExamReviewAnswerOption(
    val content: String,
    @DrawableRes val backgroundRes: Int,
)
