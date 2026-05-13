package com.example.appthitracnghiem.ui.home.history.test.general.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.ui.home.history.test.topic.HistoryTopicActivity
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class HistoryTestAdapter(val context: Context, private val listTest: ArrayList<HistoryExam>) :
    RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TestViewHolder {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val historyExam: HistoryExam = listTest[position]
        holder.binding.image.loadNetworkImage(historyExam.image)
        holder.binding.topic.text = historyExam.title
        holder.binding.detail.text = context.getString(
            R.string.format_score_short,
            historyExam.score?.toInt() ?: 0,
        )

        holder.binding.root.setOnClickListener {
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
