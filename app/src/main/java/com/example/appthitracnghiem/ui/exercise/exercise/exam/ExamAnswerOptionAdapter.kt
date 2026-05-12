package com.example.appthitracnghiem.ui.exercise.exercise.exam

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.examui.ExamTakingAnswerOption
import com.example.appthitracnghiem.model.examui.ExamTakingQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.AnswerOptionItemUi

/**
 * Danh sách đáp án khi làm bài ([FragmentExam]): chọn một đáp án, cùng khoảng cách px như code cũ.
 */
class ExamAnswerOptionAdapter : RecyclerView.Adapter<ExamAnswerOptionAdapter.OptionViewHolder>() {

    private var options: List<ExamTakingAnswerOption> = emptyList()
    private var selectedIndex: Int = -1

    var onOptionClick: ((optionIndex: Int) -> Unit)? = null

    fun submit(question: ExamTakingQuestion) {
        options = question.answerOptions
        selectedIndex = question.selectedIndexForAdapter()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = options.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_answer_option, parent, false) as TextView
        AnswerOptionItemUi.applyLegacyPxSpacing(textView)
        return OptionViewHolder(textView)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val tv = holder.textView
        tv.text = options[position].content
        tv.setBackgroundResource(
            if (position == selectedIndex) R.drawable.select_text_view else R.drawable.un_select_text_view,
        )
        tv.setOnClickListener {
            onOptionClick?.invoke(position)
        }
    }

    class OptionViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
