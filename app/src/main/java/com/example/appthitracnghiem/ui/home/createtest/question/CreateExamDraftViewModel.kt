package com.example.appthitracnghiem.ui.home.createtest.question

import androidx.lifecycle.ViewModel
import com.example.appthitracnghiem.model.CreateQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Bản nháp câu hỏi khi tạo đề trong [CreateTestActivity], dùng chung giữa
 * [FragmentCreateExam] và [FragmentReviewCreateExam] (không lưu SharedPreferences).
 */
@HiltViewModel
class CreateExamDraftViewModel @Inject constructor() : ViewModel() {

    private lateinit var _questions: ArrayList<CreateQuestion?>

    val questions: ArrayList<CreateQuestion?>
        get() = _questions

    fun initQuestionSlots(count: Int) {
        if (!::_questions.isInitialized || _questions.size != count) {
            _questions = ArrayList(List(count) { null })
        }
    }

    fun setQuestionAt(index: Int, question: CreateQuestion?) {
        if (!::_questions.isInitialized) return
        if (index in _questions.indices) {
            _questions[index] = question
        }
    }
}
