package com.example.appthitracnghiem.ui.home.home.system.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Department
import android.os.Bundle
import com.example.appthitracnghiem.ui.department.DepartmentNavExtras
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class DepartmentAdapter(
    private val listQuiz: List<Department>,
    val context: Context
) : RecyclerView.Adapter<DepartmentAdapter.ViewHolderQuiz>() {

    class ViewHolderQuiz(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.topic)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuiz {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return ViewHolderQuiz(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderQuiz, position: Int) {
        val quiz: Department = listQuiz[position]
        holder.image.loadNetworkImage(quiz.image)
        holder.title.text = quiz.title
        holder.description.text = quiz.description

        holder.itemView.setOnClickListener { v ->
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