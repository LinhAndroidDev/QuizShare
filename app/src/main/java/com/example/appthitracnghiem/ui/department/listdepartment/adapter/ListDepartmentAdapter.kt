package com.example.appthitracnghiem.ui.department.listdepartment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.model.Subject
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

class ListDepartmentAdapter(val context: Context,val listDepartment : List<DetailDepartment>) : RecyclerView.Adapter<ListDepartmentAdapter.ViewholderDepartment>(){

    class ViewholderDepartment(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameDepartment : TextView = itemView.findViewById(R.id.txtNameDepartment)
        var txtSeeAll : TextView = itemView.findViewById(R.id.txtSeeAllDepartment)
        var listSubjectDepartment : RecyclerView = itemView.findViewById(R.id.listSubjectDepartment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDepartmentAdapter.ViewholderDepartment {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_detail_department, parent, false)
        return ViewholderDepartment(itemView)
    }

    override fun onBindViewHolder(
        holder: ListDepartmentAdapter.ViewholderDepartment,
        position: Int
    ) {
        val detailDepartment : DetailDepartment = listDepartment[position]
        holder.txtNameDepartment.text = detailDepartment.title
        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        holder.listSubjectDepartment.layoutManager = linearLayoutManager
        val listSubject: MutableList<Subject> = arrayListOf()
        listSubject.add(Subject(0,R.drawable.icon_test_subject,"Toán học","Khoa học tự nhiên - 123 đề"))
        listSubject.add(Subject(1,R.drawable.icon_test_subject,"Toán học","Khoa học tự nhiên - 123 đề"))
        listSubject.add(Subject(2,R.drawable.icon_test_subject,"Toán học","Khoa học tự nhiên - 123 đề"))
        listSubject.add(Subject(3,R.drawable.icon_test_subject,"Toán học","Khoa học tự nhiên - 123 đề"))
    }

    override fun getItemCount(): Int {
        return listDepartment.size
    }
}