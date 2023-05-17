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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ExamSaved
import com.example.appthitracnghiem.ui.exercise.topic.ExerciseActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
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
        holder.description.maxLines = 1
        holder.description.ellipsize = TextUtils.TruncateAt.END
        holder.description.text = "Lượt tạo: " + examSaved.saved_num + "| Người tạo: " + examSaved.author_name

        holder.itemView.setOnClickListener {
            val activity = context as AppCompatActivity
            val mPreferenceUtil = PreferenceUtil(activity)
            mPreferenceUtil.defaultPref().edit()
                .putInt(PreferenceKey.TYPE, 1)
                .apply()
            val intent = Intent(context, ExerciseActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTestSaved.size
    }
}