package com.example.appthitracnghiem.ui.home.history.saved.test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.model.ExamSaved
import com.example.appthitracnghiem.ui.exercise.topic.ExerciseActivity
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class HistoryTestAdapter(val context: Context, private val listTestSaved: ArrayList<ExamSaved>) :
    RecyclerView.Adapter<HistoryTestAdapter.TestViewHolder>() {

    class TestViewHolder(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestViewHolder {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val examSaved: ExamSaved = listTestSaved[position]
        holder.binding.topic.text = examSaved.title
        holder.binding.image.loadNetworkImage(examSaved.image)
        holder.binding.detail.maxLines = 1
        holder.binding.detail.ellipsize = TextUtils.TruncateAt.END
        holder.binding.detail.text = context.getString(
            R.string.format_history_test_meta,
            examSaved.saved_num.toString(),
            examSaved.author_name,
        )

        holder.binding.root.setOnClickListener {
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
