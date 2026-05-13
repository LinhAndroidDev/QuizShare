package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutMenuQuestionBinding
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.model.QuestionReviewChipState

class MenuQuestionAdapter(
    private val context: Context,
    private val listQuestion: List<PositiveQuestion>,
    private val reviewChipStates: List<QuestionReviewChipState>? = null,
    private val currentQuestionIndex: Int = -1,
    /** Chế độ danh sách câu (không phải review màu): `true` nếu câu [index] đã có lựa chọn. */
    private val isExamQuestionAnswered: (Int) -> Boolean = { false },
) : RecyclerView.Adapter<MenuQuestionAdapter.ViewHolderQuestion>() {

    var onClickItem: ((Int) -> Unit)? = null

    class ViewHolderQuestion(val binding: LayoutMenuQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolderQuestion {
        val binding = LayoutMenuQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderQuestion(binding)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolderQuestion, position: Int) {
        val positiveQuestion = listQuestion[position]
        holder.binding.txtQuestion.text = positiveQuestion.number.toString()

        holder.binding.root.setOnClickListener {
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        val reviewStates = reviewChipStates
        if (reviewStates != null && reviewStates.size == listQuestion.size) {
            bindReviewMode(holder, position, reviewStates[position])
            return
        }

        bindExamMode(holder, position, positiveQuestion)
    }

    private fun bindReviewMode(holder: ViewHolderQuestion, position: Int, state: QuestionReviewChipState) {
        val tv: TextView = holder.binding.txtQuestion
        val (bg, textColor) = when (state) {
            QuestionReviewChipState.CORRECT ->
                R.drawable.bg_review_chip_correct to ContextCompat.getColor(context, R.color.black)

            QuestionReviewChipState.WRONG ->
                R.drawable.bg_review_chip_wrong to ContextCompat.getColor(context, R.color.color_red)

            QuestionReviewChipState.SKIPPED,
            QuestionReviewChipState.PENDING,
                -> R.drawable.bg_review_chip_skipped to ContextCompat.getColor(context, R.color.grey_dark)
        }
        tv.setBackgroundResource(bg)
        tv.setTextColor(textColor)

        val isCurrent = currentQuestionIndex >= 0 && position == currentQuestionIndex
        tv.typeface = if (isCurrent) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        holder.binding.root.elevation = if (isCurrent) 8f else 0f
    }

    private fun bindExamMode(holder: ViewHolderQuestion, position: Int, positiveQuestion: PositiveQuestion) {
        holder.binding.root.elevation = 0f
        holder.binding.txtQuestion.typeface = Typeface.DEFAULT

        positiveQuestion.isSelect = isExamQuestionAnswered(position)

        if (positiveQuestion.isSelect == true) {
            holder.binding.txtQuestion.setBackgroundResource(R.drawable.selected_sentence)
            holder.binding.txtQuestion.setTextColor(Color.WHITE)
        } else {
            holder.binding.txtQuestion.setBackgroundResource(R.drawable.un_selected_sentence)
            holder.binding.txtQuestion.setTextColor(ContextCompat.getColor(context, R.color.backgroundIntro))
        }
    }

    override fun getItemCount(): Int = listQuestion.size
}
