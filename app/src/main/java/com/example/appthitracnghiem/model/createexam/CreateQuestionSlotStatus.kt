package com.example.appthitracnghiem.model.createexam

/** Trạng thái hiển thị từng ô số câu trên màn tạo đề ([FragmentCreateExam]). */
enum class CreateQuestionSlotStatus {
    /** Chưa làm câu này (tương đương `-2` cũ). */
    NOT_STARTED,

    /** Đã mở nhưng câu hỏi trống / chưa hợp lệ (tương đương `-1` cũ). */
    INCOMPLETE,

    /** Đã nhập tiêu đề câu hỏi (tương đương `0` cũ). */
    COMPLETE,
}
