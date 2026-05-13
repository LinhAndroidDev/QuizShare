package com.example.appthitracnghiem.ui.home.history.saved.department.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.home.history.saved.subject.FragmentHistorySubjectSaved
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class HistoryDepartmentAdapter(val context: Context, private val listTest: List<Department>) :
    RecyclerView.Adapter<HistoryDepartmentAdapter.DepartmentViewHolder>() {

    class DepartmentViewHolder(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentViewHolder {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DepartmentViewHolder,
        position: Int
    ) {
        val department: Department = listTest[position]
        holder.binding.topic.text = department.title
        holder.binding.image.loadNetworkImage(department.image)
        holder.binding.detail.text = department.description

        holder.binding.root.setOnClickListener {
            val activity = context.findAppCompatActivity() ?: return@setOnClickListener
            val fragmentHistorySubjectSaved = FragmentHistorySubjectSaved()
            val bundle = Bundle()
            bundle.putInt("department_id_saved", department.id)
            bundle.putString("department_name_saved", department.title)
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentHistorySubjectSaved).addToBackStack(null).commit()
            fragmentHistorySubjectSaved.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}
