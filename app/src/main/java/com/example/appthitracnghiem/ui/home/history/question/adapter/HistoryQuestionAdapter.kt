package com.example.appthitracnghiem.ui.home.history.question.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appthitracnghiem.databinding.LayoutHistoryQuestionBinding
import com.example.appthitracnghiem.model.Test

class HistoryQuestionAdapter(val context: Context, val listQuestion: List<Test>) :
    Adapter<HistoryQuestionAdapter.QuestionHolder>() {

    class QuestionHolder(val binding: LayoutHistoryQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryQuestionAdapter.QuestionHolder {
        val binding = LayoutHistoryQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryQuestionAdapter.QuestionHolder, position: Int) {
        val test: Test = listQuestion[position]
        holder.binding.imageQuestionHistory.setBackgroundResource(test.image)
        holder.binding.tiltleQuestionHistory.text = test.title
        holder.binding.descripQuestionHistory.text = test.description
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}
