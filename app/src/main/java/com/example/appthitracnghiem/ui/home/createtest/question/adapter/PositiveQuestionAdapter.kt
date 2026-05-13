package com.example.appthitracnghiem.ui.home.createtest.question.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutPositionQuestionBinding
import com.example.appthitracnghiem.model.createexam.CreateQuestionSlotStatus

class PositiveQuestionAdapter(
    private val max: Int,
    private val context: Context,
    private val slotStatus: (Int) -> CreateQuestionSlotStatus,
) : RecyclerView.Adapter<PositiveQuestionAdapter.PositiveViewHolder>() {
    private var selectedIndex: Int = 0
    var onClickItem: ((Int) -> Unit)? = null

    class PositiveViewHolder(val binding: LayoutPositionQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositiveViewHolder {
        val binding = LayoutPositionQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PositiveViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: PositiveViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val txtPositive: TextView = holder.binding.txtPositive
        txtPositive.text = (position + 1).toString()

        holder.binding.root.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        if (selectedIndex == position) {
            txtPositive.setTextColor(Color.WHITE)
            txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
        } else {
            when (slotStatus(position)) {
                CreateQuestionSlotStatus.NOT_STARTED -> {
                    txtPositive.setTextColor(Color.BLACK)
                }

                CreateQuestionSlotStatus.INCOMPLETE -> {
                    txtPositive.setTextColor(ContextCompat.getColor(context, R.color.pink_red))
                }

                CreateQuestionSlotStatus.COMPLETE -> {
                    txtPositive.setTextColor(ContextCompat.getColor(context, R.color.backgroundIntro))
                }
            }
            txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }
    }

    override fun getItemCount(): Int = max

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        notifyDataSetChanged()
    }
}
