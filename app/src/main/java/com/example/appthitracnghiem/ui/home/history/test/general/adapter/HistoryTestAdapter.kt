package com.example.appthitracnghiem.ui.home.history.test.general.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.home.history.test.HistoryTopicActivity
import com.squareup.picasso.Picasso

class HistoryTestAdapter(val context: Context, private val listTest: ArrayList<HistoryExam>) : RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return TestViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val historyExam: HistoryExam = listTest[position]
        Picasso.get().load(historyExam.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.title.text = historyExam.title
        holder.description.text = historyExam.score?.toInt().toString() + " điểm"

        holder.itemView.setOnClickListener{
            val activity = it.context as AppCompatActivity
            val intent = Intent(activity,HistoryTopicActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}