package com.example.appthitracnghiem.ui.home.home.system.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.department.DepartmentNavExtras
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class DepartmentAdapter(
    private val listQuiz: List<Department>,
    val context: Context
) : RecyclerView.Adapter<DepartmentAdapter.ViewHolderQuiz>() {

    class ViewHolderQuiz(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuiz {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderQuiz(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderQuiz, position: Int) {
        val quiz: Department = listQuiz[position]
        holder.binding.image.loadNetworkImage(quiz.image)
        holder.binding.topic.text = quiz.title
        holder.binding.detail.text = quiz.description

        holder.binding.root.setOnClickListener { v ->
            val activity = v.context.findAppCompatActivity() ?: return@setOnClickListener
            val fragmentListDepartment = FragmentListDepartment().apply {
                arguments = Bundle().apply {
                    putInt(DepartmentNavExtras.ARG_DEPARTMENT_ID, quiz.id)
                    putInt(DepartmentNavExtras.ARG_LIST_SOURCE_TYPE, 0)
                }
            }
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentListDepartment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}
