package com.example.appthitracnghiem.ui.home.createtest.review

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R

class PositionReviewAdapter(private val max: Int, context: Context) : RecyclerView.Adapter<PositionReviewAdapter.ReviewViewHolder>() {
    private var selectedIndex: Int = 0
    var number: Int = -1
    var onClickItem: ((Int)->Unit)? = null

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtPositive: TextView = itemView.findViewById(R.id.txtPositive)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PositionReviewAdapter.ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_position_question,parent,false)
        return ReviewViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: PositionReviewAdapter.ReviewViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.txtPositive.text = (position + 1).toString()

        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        if (selectedIndex == position) {
            holder.txtPositive.setTextColor(Color.WHITE)
            holder.txtPositive.setBackgroundResource(R.drawable.select_position_quiz_review)
        } else {
            holder.txtPositive.setTextColor(Color.BLACK)
            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
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