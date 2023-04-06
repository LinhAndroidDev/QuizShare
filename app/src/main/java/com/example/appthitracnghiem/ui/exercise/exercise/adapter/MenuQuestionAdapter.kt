package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion

class MenuQuestionAdapter(val context: Context, private val listQuestion: List<PositiveQuestion>)
    : RecyclerView.Adapter<MenuQuestionAdapter.ViewHolderQuestion>() {

    class ViewHolderQuestion(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtQuestion: TextView = itemView.findViewById(R.id.txtQuestion)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MenuQuestionAdapter.ViewHolderQuestion {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_question,parent,false)
        return ViewHolderQuestion(itemView)
    }

    override fun onBindViewHolder(holder: MenuQuestionAdapter.ViewHolderQuestion, position: Int) {
        val positiveQuestion: PositiveQuestion = listQuestion[position]
        holder.txtQuestion.text = positiveQuestion.number.toString()
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}