package com.example.appthitracnghiem.ui.exercise.exercise.answer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.examui.ExamReviewAnswerOption
import com.example.appthitracnghiem.model.examui.ExamReviewQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.AnswerOptionItemUi

/**
 * Danh sách đáp án trong màn xem lại bài thi ([FragmentAnswer]).
 * Giữ cùng quy tắc viền màu như trước (đúng / sai / bỏ qua).
 */
class AnswerReviewOptionAdapter : RecyclerView.Adapter<AnswerReviewOptionAdapter.OptionViewHolder>() {

    private var options: List<ExamReviewAnswerOption> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submit(question: ExamReviewQuestion) {
        options = question.options
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = options.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_answer_option, parent, false) as TextView
        textView.isClickable = false
        textView.isEnabled = false
        AnswerOptionItemUi.applyLegacyPxSpacing(textView)
        return OptionViewHolder(textView)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val row = options[position]
        holder.textView.text = row.content
        holder.textView.setBackgroundResource(row.backgroundRes)
    }

    class OptionViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
