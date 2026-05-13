package com.example.appthitracnghiem.ui.home.history.saved.subject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.ui.home.history.saved.test.FragmentHistoryTestSaved
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class HistorySubjectAdapter(val context: Context, private val listSubject: ArrayList<SaveSubjectResponse.Result>?) :
    RecyclerView.Adapter<HistorySubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectViewHolder {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val savedSubject: SaveSubjectResponse.Result = listSubject!![position]
        holder.binding.topic.text = savedSubject.title
        holder.binding.image.loadNetworkImage(savedSubject.image)
        holder.binding.detail.text = context.getString(
            R.string.format_subject_exam_line,
            savedSubject.department_title,
            savedSubject.exem_number,
        )

        holder.binding.root.setOnClickListener {
            val activity = context.findAppCompatActivity() ?: return@setOnClickListener
            val bundle = Bundle()
            bundle.putInt("subject_saved_id", savedSubject.id)
            val fragmentHistoryTestSaved = FragmentHistoryTestSaved()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentHistoryTestSaved).addToBackStack(null).commit()
            fragmentHistoryTestSaved.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listSubject!!.size
    }
}
