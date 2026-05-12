package com.example.appthitracnghiem.model.examui

import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Answer
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.QuestionReviewChipState

/**
 * Một câu trên màn xem lại bài: DTO + danh sách đáp án đã gán background + chip menu.
 */
data class ExamReviewQuestion(
    val source: ExamQuestion,
    val options: List<ExamReviewAnswerOption>,
    val chipState: QuestionReviewChipState,
) {

    val isUserAnswered: Boolean
        get() = chipState == QuestionReviewChipState.CORRECT ||
            chipState == QuestionReviewChipState.WRONG

    companion object {
        fun fromApi(source: ExamQuestion, resultByQuestionId: Map<Int, Int?>?): ExamReviewQuestion {
            val questionId = source.question_id
            val userAid = resultByQuestionId?.get(questionId)
            val chipState = when {
                resultByQuestionId == null -> QuestionReviewChipState.PENDING
                userAid == null -> QuestionReviewChipState.SKIPPED
                else -> {
                    val chosen = source.answer_list.find { it.answer_id == userAid }
                    if (chosen?.type == 1) QuestionReviewChipState.CORRECT else QuestionReviewChipState.WRONG
                }
            }
            val options = source.answer_list.map { answer ->
                ExamReviewAnswerOption(
                    content = answer.content,
                    backgroundRes = backgroundForAnswer(answer, questionId, resultByQuestionId),
                )
            }
            return ExamReviewQuestion(source = source, options = options, chipState = chipState)
        }

        private fun backgroundForAnswer(
            answer: Answer,
            questionId: Int,
            resultMap: Map<Int, Int?>?,
        ): Int {
            if (resultMap == null) return R.drawable.un_select_text_view
            val userAnswerId: Int? = resultMap[questionId]
            val isCorrect = answer.type == 1
            val userPickedThis = userAnswerId != null && userAnswerId == answer.answer_id
            val unanswered = userAnswerId == null

            return when {
                unanswered && isCorrect -> R.drawable.bg_answer_fail
                userPickedThis && isCorrect -> R.drawable.bg_answer_border_green
                userPickedThis && !isCorrect -> R.drawable.bg_answer_fail
                !userPickedThis && isCorrect && userAnswerId != null -> R.drawable.bg_answer_border_green
                else -> R.drawable.un_select_text_view
            }
        }
    }
}
