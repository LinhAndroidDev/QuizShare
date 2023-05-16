package com.example.appthitracnghiem.ui.home.history.saved.department.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.home.history.saved.subject.FragmentHistorySubjectSaved
import com.squareup.picasso.Picasso

class HistoryDepartmentAdapter(val context: Context, private val listTest: List<Department>) :
    RecyclerView.Adapter<HistoryDepartmentAdapter.DepartmentViewHolder>() {

    class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return DepartmentViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: DepartmentViewHolder,
        position: Int
    ) {
        val department : Department = listTest[position]
        holder.title.text = department.title
        Picasso.get().load(department.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.description.text = department.description

        holder.itemView.setOnClickListener {
            val activity = context as AppCompatActivity
            val fragmentHistorySubjectSaved = FragmentHistorySubjectSaved()
            val bundle = Bundle()
            bundle.putInt("department_id_saved", department.id)
            bundle.putString("department_name_saved", department.title)
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistorySubjectSaved).addToBackStack(null).commit()
            fragmentHistorySubjectSaved.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}