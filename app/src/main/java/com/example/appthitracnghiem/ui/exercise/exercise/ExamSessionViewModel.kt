package com.example.appthitracnghiem.ui.exercise.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.examui.ExamTakingQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Phiên làm bài trong [ExamActivity]: danh sách [ExamTakingQuestion] (mỗi câu + đáp án UI + chọn/đã xem).
 */
@HiltViewModel
class ExamSessionViewModel @Inject constructor() : ViewModel() {

    private val _questions = MutableLiveData<List<ExamTakingQuestion>>()

    fun initWithQuestions(questions: List<ExamQuestion>) {
        if (questions.isEmpty()) return
        val existing = _questions.value
        if (existing != null &&
            existing.size == questions.size &&
            existing.map { it.source.question_id } == questions.map { it.question_id }
        ) {
            return
        }
        _questions.value = questions.mapIndexed { index, q ->
            ExamTakingQuestion.fromApi(q, initiallyVisited = index == 0)
        }
    }

    fun questionAt(index: Int): ExamTakingQuestion? = _questions.value?.getOrNull(index)

    fun isQuestionAnswered(questionIndex: Int): Boolean =
        questionAt(questionIndex)?.isAnswered() == true

    fun ensureVisited(questionIndex: Int) {
        val list = _questions.value ?: return
        if (questionIndex !in list.indices) return
        val q = list[questionIndex]
        if (q.hasBeenVisited) return
        replaceQuestion(questionIndex, q.copy(hasBeenVisited = true))
    }

    fun setSelection(questionIndex: Int, optionIndex: Int) {
        val list = _questions.value ?: return
        if (questionIndex !in list.indices) return
        val q = list[questionIndex]
        if (optionIndex !in q.answerOptions.indices) return
        replaceQuestion(
            questionIndex,
            q.copy(hasBeenVisited = true, selectedIndex = optionIndex),
        )
    }

    /** Mỗi phần tử: index đáp án đã chọn hoặc `-1` (bỏ qua / chưa chọn), thứ tự theo câu hỏi. */
    fun selectedOptionIndicesForScoring(): List<Int> {
        val list = _questions.value ?: return emptyList()
        return list.map { it.selectedIndexForScoring() }
    }

    fun snapshotIndices(): ArrayList<Int> = ArrayList(selectedOptionIndicesForScoring())

    /** Thoát giữa chừng: mọi câu coi như đã xem, chưa chọn đáp án. */
    fun markAllUnanswered(questionCount: Int) {
        val list = _questions.value ?: return
        if (list.size != questionCount) return
        _questions.value = list.map { it.copy(hasBeenVisited = true, selectedIndex = -1) }
    }

    private fun replaceQuestion(index: Int, updated: ExamTakingQuestion) {
        val list = _questions.value ?: return
        val out = list.toMutableList()
        out[index] = updated
        _questions.value = out.toList()
    }
}
