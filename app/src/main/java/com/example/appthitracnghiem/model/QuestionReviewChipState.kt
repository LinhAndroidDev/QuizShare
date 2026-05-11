package com.example.appthitracnghiem.model

/** Trạng thái hiển thị số câu trong menu (xem lại bài thi). */
enum class QuestionReviewChipState {
    /** Chọn đúng đáp án (theo `type == 1`). */
    CORRECT,

    /** Đã chọn nhưng sai. */
    WRONG,

    /** Không chọn / bỏ qua. */
    SKIPPED,

    /** Chưa có `exam_result` (API chưa trả). */
    PENDING,
}
