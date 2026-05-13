package com.example.appthitracnghiem.ui.exercise.exercise.exam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.examui.ExamTakingQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(
    private val apiService: ApiService,
) : BaseViewModel() {

    val listExamQuestionLiveData = MutableLiveData<ArrayList<ExamQuestion>?>()
    val title = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getExamListQuestion(requestExamQuestion: RequestExamQuestion) {
        loadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.getExamListQuestion(requestExamQuestion) }
            loadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listExamQuestionLiveData.value = result.data.result.exam_question_list
                is ResultState.Error -> {
                    errorApiLiveData.value = result.message
                }
            }
        }
    }

    // --- Phiên làm bài (trước đây ExamSessionViewModel) ---

    private val _takingQuestions = MutableLiveData<List<ExamTakingQuestion>>()

    fun initWithQuestions(questions: List<ExamQuestion>) {
        if (questions.isEmpty()) return
        val existing = _takingQuestions.value
        if (existing != null &&
            existing.size == questions.size &&
            existing.map { it.source.question_id } == questions.map { it.question_id }
        ) {
            return
        }
        _takingQuestions.value = questions.mapIndexed { index, q ->
            ExamTakingQuestion.fromApi(q, initiallyVisited = index == 0)
        }
    }

    fun questionAt(index: Int): ExamTakingQuestion? = _takingQuestions.value?.getOrNull(index)

    fun isQuestionAnswered(questionIndex: Int): Boolean =
        questionAt(questionIndex)?.isAnswered() == true

    fun ensureVisited(questionIndex: Int) {
        val list = _takingQuestions.value ?: return
        if (questionIndex !in list.indices) return
        val q = list[questionIndex]
        if (q.hasBeenVisited) return
        replaceTakingQuestion(questionIndex, q.copy(hasBeenVisited = true))
    }

    fun setSelection(questionIndex: Int, optionIndex: Int) {
        val list = _takingQuestions.value ?: return
        if (questionIndex !in list.indices) return
        val q = list[questionIndex]
        if (optionIndex !in q.answerOptions.indices) return
        replaceTakingQuestion(
            questionIndex,
            q.copy(hasBeenVisited = true, selectedIndex = optionIndex),
        )
    }

    private fun selectedOptionIndicesForScoring(): List<Int> {
        val list = _takingQuestions.value ?: return emptyList()
        return list.map { it.selectedIndexForScoring() }
    }

    fun snapshotIndices(): ArrayList<Int> = ArrayList(selectedOptionIndicesForScoring())

    fun markAllUnanswered(questionCount: Int) {
        val list = _takingQuestions.value ?: return
        if (list.size != questionCount) return
        _takingQuestions.value = list.map { it.copy(hasBeenVisited = true, selectedIndex = -1) }
    }

    private fun replaceTakingQuestion(index: Int, updated: ExamTakingQuestion) {
        val list = _takingQuestions.value ?: return
        val out = list.toMutableList()
        out[index] = updated
        _takingQuestions.value = out.toList()
    }
}
