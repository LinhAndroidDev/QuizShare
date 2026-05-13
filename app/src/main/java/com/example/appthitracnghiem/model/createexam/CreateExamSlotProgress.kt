package com.example.appthitracnghiem.model.createexam

/**
 * Quản lý tiến độ từng câu trên màn tạo đề (thanh chọn số câu), không dùng SharedPreferences.
 */
class CreateExamSlotProgress(questionCount: Int) {

    private val statuses = MutableList(questionCount) { CreateQuestionSlotStatus.NOT_STARTED }

    fun statusAt(index: Int): CreateQuestionSlotStatus =
        statuses.getOrElse(index) { CreateQuestionSlotStatus.NOT_STARTED }

    fun markIncomplete(index: Int) {
        if (index in statuses.indices) {
            statuses[index] = CreateQuestionSlotStatus.INCOMPLETE
        }
    }

    fun markComplete(index: Int) {
        if (index in statuses.indices) {
            statuses[index] = CreateQuestionSlotStatus.COMPLETE
        }
    }

    fun allSlotsComplete(): Boolean =
        statuses.all { it == CreateQuestionSlotStatus.COMPLETE }
}
