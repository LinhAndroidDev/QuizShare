package com.example.appthitracnghiem.ui.exercise.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Trạng thái làm bài trong [ExamActivity]: mỗi câu lưu **index đáp án đã chọn** trong [ExamQuestion.answer_list],
 * hoặc `-1` (bỏ qua / chưa chọn), `-2` (chưa mở câu — chỉ dùng lúc khởi tạo, sẽ về `-1` khi vào câu).
 */
@HiltViewModel
class ExamSessionViewModel @Inject constructor() : ViewModel() {

    private val _answers = MutableLiveData<ArrayList<Int>>()

    fun initAnswers(questionCount: Int) {
        if (questionCount <= 0) return
        if (_answers.value?.size == questionCount) return
        val list = ArrayList<Int>(questionCount)
        for (i in 0 until questionCount) {
            list.add(if (i == 0) -1 else -2)
        }
        _answers.value = list
    }

    fun isQuestionAnswered(questionIndex: Int): Boolean {
        val list = _answers.value ?: return false
        val v = list.getOrNull(questionIndex) ?: return false
        return v >= 0
    }

    fun getSelection(questionIndex: Int): Int =
        _answers.value?.getOrNull(questionIndex) ?: -1

    /** Khi hiển thị câu: `-2` (chưa vào) → `-1` (đã xem, chưa chọn). */
    fun ensureVisited(questionIndex: Int) {
        val current = _answers.value ?: return
        if (current.getOrNull(questionIndex) != -2) return
        val copy = ArrayList(current)
        copy[questionIndex] = -1
        _answers.value = copy
    }

    fun setSelection(questionIndex: Int, optionIndex: Int) {
        val current = _answers.value ?: return
        if (questionIndex !in current.indices) return
        val copy = ArrayList(current)
        copy[questionIndex] = optionIndex
        _answers.value = copy
    }

    fun snapshot(): ArrayList<Int> = ArrayList(_answers.value ?: arrayListOf())

    /** Thoát giữa chừng: coi như mọi câu chưa trả lời. */
    fun markAllUnanswered(questionCount: Int) {
        if (questionCount <= 0) return
        _answers.value = ArrayList(List(questionCount) { -1 })
    }
}
