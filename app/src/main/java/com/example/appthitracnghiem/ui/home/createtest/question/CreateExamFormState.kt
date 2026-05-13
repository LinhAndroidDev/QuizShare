package com.example.appthitracnghiem.ui.home.createtest.question

import java.io.Serializable

/**
 * Thông tin form tạo đề, dùng chung [FragmentCreateTest] → [CreateTestActivity] →
 * [FragmentCreateExam] / [FragmentReviewCreateExam] qua [CreateExamViewModel] (phạm vi activity).
 */
data class CreateExamFormState(
    val numberQuestion: Int,
    val title: String,
    val timeMinutes: Int,
    val status: Int,
    val subjectId: Int,
    val departmentLabel: String,
    val describe: String,
    val coverUri: String,
) : Serializable
