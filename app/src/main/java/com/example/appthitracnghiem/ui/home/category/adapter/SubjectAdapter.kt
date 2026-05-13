package com.example.appthitracnghiem.ui.home.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.databinding.LayoutSubjectBinding
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.utils.loadNetworkImage

class SubjectAdapter(private val listSubject: ArrayList<Subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    class SubjectViewHolder(val binding: LayoutSubjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = LayoutSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject: Subject = listSubject[position]
        holder.binding.imageSubject.loadNetworkImage(subject.image)
        holder.binding.nameSubject.text = subject.title
//        holder.binding.detailSubject.text = subject.description
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }
}
