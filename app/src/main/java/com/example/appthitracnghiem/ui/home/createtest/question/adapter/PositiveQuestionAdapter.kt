package com.example.appthitracnghiem.ui.home.createtest.question.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R

class PositiveQuestionAdapter(private val max: Int, val context: Context) :
    RecyclerView.Adapter<PositiveQuestionAdapter.PositiveViewHolder>() {
    private var selectedIndex: Int = 0
    var number: Int = -1
    var onClickItem: ((Int)->Unit)? = null

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
//        val positiveQuestion: PositiveQuestion = listPossitive[position]
        holder.txtPositive.text = (position + 1).toString()

        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

//        if (number == position) {
//            holder.txtPositive.setTextColor(Color.WHITE)
//            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
//        } else {
//            holder.txtPositive.setTextColor(Color.BLACK)
//            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
//        }

        if (selectedIndex == position) {
            holder.txtPositive.setTextColor(Color.WHITE)
            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
        } else {
            holder.txtPositive.setTextColor(Color.BLACK)
            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }
    }

    override fun getItemCount(): Int {
        return max
    }

    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        this.notifyDataSetChanged()
    }
}