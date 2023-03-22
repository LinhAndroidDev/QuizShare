package com.example.appthitracnghiem.ui.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Quiz
import com.squareup.picasso.Picasso

class QuizAdapter(val listQuiz : List<Quiz>,val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolderQuiz>() {
    class ViewHolderQuiz(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView = itemView.findViewById(R.id.imageQuiz)
        var topic : TextView = itemView.findViewById(R.id.topicQuiz)
        var detail : TextView = itemView.findViewById(R.id.detailQuiz)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuiz {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_quiz,parent,false)
        return ViewHolderQuiz(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderQuiz, position: Int) {
        var quiz : Quiz = listQuiz[position]
        Picasso.get().load(quiz.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.topic.text = quiz.topic
        holder.detail.text = quiz.detail
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}