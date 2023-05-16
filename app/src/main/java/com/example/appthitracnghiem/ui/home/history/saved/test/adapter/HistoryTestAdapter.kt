package com.example.appthitracnghiem.ui.home.history.saved.test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ExamSaved
import com.squareup.picasso.Picasso

class HistoryTestAdapter(val context: Context, private val listTestSaved: ArrayList<ExamSaved>) : RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return TestViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val examSaved : ExamSaved = listTestSaved[position]
        holder.title.text = examSaved.title
        Picasso.get().load(examSaved.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.description.text = "Lượt tạo: " + examSaved.saved_num + "| Người tạo: " + examSaved.author_name
    }

    override fun getItemCount(): Int {
        return listTestSaved.size
    }
}