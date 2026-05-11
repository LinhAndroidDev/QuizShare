package com.example.appthitracnghiem.ui.exercise.exercise.answer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Answer
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.AnswerOptionItemUi

/**
 * Danh sách đáp án trong màn xem lại bài thi ([FragmentAnswer]).
 * Giữ cùng quy tắc viền màu như trước (đúng / sai / bỏ qua).
 */
class AnswerReviewOptionAdapter : RecyclerView.Adapter<AnswerReviewOptionAdapter.OptionViewHolder>() {

    private var answers: List<Answer> = emptyList()
    private var questionId: Int = 0
    private var resultMap: Map<Int, Int?>? = null

    fun submit(question: ExamQuestion, examResult: Map<Int, Int?>?) {
        questionId = question.question_id
        answers = question.answer_list.toList()
        resultMap = examResult
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = answers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_answer_option, parent, false) as TextView
        textView.isClickable = false
        textView.isEnabled = false
        AnswerOptionItemUi.applyLegacyPxSpacing(textView)
        return OptionViewHolder(textView)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val answer = answers[position]
        holder.textView.text = answer.content
        holder.textView.setBackgroundResource(backgroundFor(answer))
    }

    class OptionViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    private fun backgroundFor(answer: Answer): Int {
        val map = resultMap ?: return R.drawable.un_select_text_view
        val userAnswerId: Int? = map[questionId]
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
