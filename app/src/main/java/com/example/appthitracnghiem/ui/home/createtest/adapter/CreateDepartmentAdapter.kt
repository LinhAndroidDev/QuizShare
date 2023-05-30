package com.example.appthitracnghiem.ui.home.createtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R

class CreateDepartmentAdapter(
    private val listTopic: ArrayList<String>,
    context: Context
) : RecyclerView.Adapter<CreateDepartmentAdapter.ViewHolder>() {
    var onClickItem: ((Int) -> Unit)? = null
    var getStringItem: ((String) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTopic: TextView = itemView.findViewById(R.id.txtTopic)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreateDepartmentAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_topic, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreateDepartmentAdapter.ViewHolder, position: Int) {
        holder.txtTopic.text = listTopic[position]

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(position)
            getStringItem?.invoke(listTopic[position])
        }
    }

    override fun getItemCount(): Int {
        return listTopic.size
    }
}