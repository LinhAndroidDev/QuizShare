package com.example.appthitracnghiem.ui.home.createtest.review

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutPositionQuestionBinding

class PositionReviewAdapter(private val max: Int, @Suppress("UNUSED_PARAMETER") context: Context) :
    RecyclerView.Adapter<PositionReviewAdapter.ReviewViewHolder>() {
    private var selectedIndex: Int = 0
    var number: Int = -1
    var onClickItem: ((Int) -> Unit)? = null

    class ReviewViewHolder(val binding: LayoutPositionQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PositionReviewAdapter.ReviewViewHolder {
        val binding = LayoutPositionQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(
        holder: PositionReviewAdapter.ReviewViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.txtPositive.text = (position + 1).toString()

        holder.binding.root.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        if (selectedIndex == position) {
            holder.binding.txtPositive.setTextColor(Color.WHITE)
            holder.binding.txtPositive.setBackgroundResource(R.drawable.select_position_quiz_review)
        } else {
            holder.binding.txtPositive.setTextColor(Color.BLACK)
            holder.binding.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }
    }

    override fun getItemCount(): Int {
        return max
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        this.notifyDataSetChanged()
    }
}
