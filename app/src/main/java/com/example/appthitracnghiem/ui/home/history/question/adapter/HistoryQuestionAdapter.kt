package com.example.appthitracnghiem.ui.home.history.question.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test

class HistoryQuestionAdapter(val context: Context,val listQuestion: List<Test>) : Adapter<HistoryQuestionAdapter.QuestionHolder>() {

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageQuestionHistory)
        val title: TextView = itemView.findViewById(R.id.tiltleQuestionHistory)
        val description: TextView = itemView.findViewById(R.id.descripQuestionHistory)
        val delete: TextView = itemView.findViewById(R.id.deleteQuestionHistory)
        val edit: TextView = itemView.findViewById(R.id.editQuestionHistory)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryQuestionAdapter.QuestionHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_history_question,parent,false)
        return QuestionHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryQuestionAdapter.QuestionHolder, position: Int) {
        val test: Test = listQuestion[position]
        holder.image.setBackgroundResource(test.image)
        holder.title.text = test.title
        holder.description.text = test.description

        val semibold: Typeface? = ResourcesCompat.getFont(context,R.font.svn_gilroy_semibold)
        holder.title.typeface = semibold
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}