package com.example.appthitracnghiem.ui.home.createtest.question.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.createexam.CreateQuestionSlotStatus

class PositiveQuestionAdapter(
    private val max: Int,
    private val context: Context,
    private val slotStatus: (Int) -> CreateQuestionSlotStatus,
) : RecyclerView.Adapter<PositiveQuestionAdapter.PositiveViewHolder>() {
    private var selectedIndex: Int = 0
    var onClickItem: ((Int) -> Unit)? = null

    class PositiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtPositive: TextView = itemView.findViewById(R.id.txtPositive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositiveViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_position_question, parent, false)
        return PositiveViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: PositiveViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.txtPositive.text = (position + 1).toString()

        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        if (selectedIndex == position) {
            holder.txtPositive.setTextColor(Color.WHITE)
            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
        } else {
            when (slotStatus(position)) {
                CreateQuestionSlotStatus.NOT_STARTED -> {
                    holder.txtPositive.setTextColor(Color.BLACK)
                }

                CreateQuestionSlotStatus.INCOMPLETE -> {
                    holder.txtPositive.setTextColor(ContextCompat.getColor(context, R.color.pink_red))
                }

                CreateQuestionSlotStatus.COMPLETE -> {
                    holder.txtPositive.setTextColor(ContextCompat.getColor(context, R.color.backgroundIntro))
                }
            }
            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }
    }

    override fun getItemCount(): Int = max

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        notifyDataSetChanged()
    }
}
