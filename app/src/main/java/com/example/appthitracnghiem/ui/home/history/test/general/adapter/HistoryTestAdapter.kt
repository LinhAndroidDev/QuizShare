package com.example.appthitracnghiem.ui.home.history.test.general.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.home.history.test.topic.HistoryTopicActivity
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class HistoryTestAdapter(val context: Context, private val listTest: ArrayList<HistoryExam>) :
    RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return TestViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val historyExam: HistoryExam = listTest[position]
        holder.image.loadNetworkImage(historyExam.image)
        holder.title.text = historyExam.title
        holder.description.text = context.getString(
            R.string.format_score_short,
            historyExam.score?.toInt() ?: 0,
        )

        holder.itemView.setOnClickListener {
            val activity = it.context.findAppCompatActivity() ?: return@setOnClickListener
            val historyId = historyExam.exam_history_id ?: return@setOnClickListener
            val intent = Intent(activity, HistoryTopicActivity::class.java).apply {
                putExtra(ExamSessionExtras.INTENT_EXAM_HISTORY_ID, historyId)
            }
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}