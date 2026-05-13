package com.example.appthitracnghiem.ui.home.createtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.databinding.LayoutListTopicBinding

class CreateDepartmentAdapter(
    private val listTopic: ArrayList<String>,
    @Suppress("UNUSED_PARAMETER") context: Context,
) : RecyclerView.Adapter<CreateDepartmentAdapter.ViewHolder>() {
    var onClickItem: ((Int) -> Unit)? = null
    var getStringItem: ((String) -> Unit)? = null

    class ViewHolder(val binding: LayoutListTopicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreateDepartmentAdapter.ViewHolder {
        val binding = LayoutListTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreateDepartmentAdapter.ViewHolder, position: Int) {
        holder.binding.txtTopic.text = listTopic[position]

        holder.binding.root.setOnClickListener {
            onClickItem?.invoke(position)
            getStringItem?.invoke(listTopic[position])
        }
    }

    override fun getItemCount(): Int {
        return listTopic.size
    }
}
