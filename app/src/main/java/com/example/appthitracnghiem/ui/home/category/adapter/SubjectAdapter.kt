package com.example.appthitracnghiem.ui.home.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.utils.loadNetworkImage

class SubjectAdapter(private val listSubject: ArrayList<Subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageSubject: ImageView = itemView.findViewById(R.id.imageSubject)
        var nameSubject: TextView = itemView.findViewById(R.id.nameSubject)
        var detailSubject: TextView = itemView.findViewById(R.id.detailSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_subject, parent, false)
        return SubjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject: Subject = listSubject[position]
        holder.imageSubject.loadNetworkImage(subject.image)
        holder.nameSubject.text = subject.title
//        holder.detailSubject.text = subject.description
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }
}