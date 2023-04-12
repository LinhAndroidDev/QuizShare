package com.example.appthitracnghiem.ui.home.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.RecommandSubject
import com.squareup.picasso.Picasso

class SubjectAdapter(private val listSubject: List<RecommandSubject>, val context: Context) :
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
        val subject: RecommandSubject = listSubject[position]
        Picasso.get().load(subject.imageSubject)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.imageSubject)
        holder.nameSubject.text = subject.nameSubject
        holder.detailSubject.text = subject.detailSubject
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }
}