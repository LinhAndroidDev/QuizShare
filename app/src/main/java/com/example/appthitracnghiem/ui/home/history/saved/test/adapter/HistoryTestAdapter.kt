package com.example.appthitracnghiem.ui.home.history.saved.test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ExamSaved
import com.example.appthitracnghiem.ui.exercise.topic.ExerciseActivity
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

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
        holder.image.loadNetworkImage(examSaved.image)
        holder.description.maxLines = 1
        holder.description.ellipsize = TextUtils.TruncateAt.END
        holder.description.text = context.getString(
            R.string.format_history_test_meta,
            examSaved.saved_num.toString(),
            examSaved.author_name,
        )

        holder.itemView.setOnClickListener {
            val activity = context.findAppCompatActivity() ?: return@setOnClickListener
            val intent = Intent(activity, ExerciseActivity::class.java).apply {
                putExtra(ExamSessionExtras.INTENT_EXAM_ID, examSaved.id)
                putExtra(ExamSessionExtras.INTENT_TIME_MINUTES, examSaved.time)
                putExtra(ExamSessionExtras.INTENT_TOPIC_UI_MODE, 1)
                val prefs = PreferenceUtil(activity).defaultPref()
                putExtra(
                    ExamSessionExtras.INTENT_TOPIC_USER_NAME,
                    prefs.getString(PreferenceKey.USER_NAME, "").orEmpty(),
                )
                putExtra(
                    ExamSessionExtras.INTENT_TOPIC_USER_AVATAR,
                    prefs.getString(PreferenceKey.USER_AVATAR, "").orEmpty(),
                )
            }
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTestSaved.size
    }
}