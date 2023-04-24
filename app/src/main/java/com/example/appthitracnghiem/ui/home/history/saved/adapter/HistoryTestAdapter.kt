package com.example.appthitracnghiem.ui.home.history.saved.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test

class HistoryTestAdapter(val context: Context, val listTest: List<Test>) : RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryTestAdapter.TestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return TestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryTestAdapter.TestViewHolder, position: Int) {
        val test : Test = listTest[position]
        holder.title.text = test.title
        holder.image.setImageResource(test.image)
        holder.description.text = test.description
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}