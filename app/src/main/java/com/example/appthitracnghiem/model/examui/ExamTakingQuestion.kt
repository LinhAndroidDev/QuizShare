package com.example.appthitracnghiem.model.examui

import com.example.appthitracnghiem.model.ExamQuestion

/**
 * Một câu hỏi trên màn làm bài: giữ [source] (DTO) + danh sách đáp án UI + trạng thái chọn.
 */
data class ExamTakingQuestion(
    val source: ExamQuestion,
    val answerOptions: List<ExamTakingAnswerOption>,
    val hasBeenVisited: Boolean,
    val selectedIndex: Int = -1,
) {

    /** Index đáp án đang chọn cho [ExamAnswerOptionAdapter], `-1` = chưa chọn / chưa vào câu. */
    fun selectedIndexForAdapter(): Int =
        when {
            !hasBeenVisited -> -1
            selectedIndex < 0 -> -1
            selectedIndex >= answerOptions.size -> -1
            else -> selectedIndex
        }

    fun isAnswered(): Boolean = hasBeenVisited && selectedIndex >= 0 && selectedIndex < answerOptions.size

    /** Gửi điểm: index trong [answerOptions], hoặc `-1` nếu không nộp đáp án. */
    fun selectedIndexForScoring(): Int =
        when {
            !hasBeenVisited -> -1
            selectedIndex < 0 -> -1
            selectedIndex >= answerOptions.size -> -1
            else -> selectedIndex
        }

    companion object {
        fun fromApi(source: ExamQuestion, initiallyVisited: Boolean): ExamTakingQuestion =
            ExamTakingQuestion(
                source = source,
                answerOptions = source.answer_list.map { a ->
                    ExamTakingAnswerOption(
                        answerId = a.answer_id,
                        content = a.content,
                        isCorrect = a.type == 1,
                    )
                },
                hasBeenVisited = initiallyVisited,
                selectedIndex = -1,
            )
    }
}
