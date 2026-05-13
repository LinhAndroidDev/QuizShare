package com.example.appthitracnghiem.ui.home.home.user.adapter

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

class FromUserAdapter(
    private val listQuiz: List<Department>,
    val context: Context
) : RecyclerView.Adapter<FromUserAdapter.ViewHolderQuiz>() {

    class ViewHolderQuiz(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FromUserAdapter.ViewHolderQuiz {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderQuiz(binding)
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }

    override fun onBindViewHolder(holder: FromUserAdapter.ViewHolderQuiz, position: Int) {
        val quiz: Department = listQuiz[position]
        holder.binding.image.loadNetworkImage(quiz.image)
        holder.binding.topic.text = quiz.title
        holder.binding.detail.text = quiz.description

        holder.binding.root.setOnClickListener { v ->
            val activity = v.context.findAppCompatActivity() ?: return@setOnClickListener
            val fragmentListDepartment = FragmentListDepartment().apply {
                arguments = Bundle().apply {
                    putInt(DepartmentNavExtras.ARG_DEPARTMENT_ID, quiz.id)
                    putInt(DepartmentNavExtras.ARG_LIST_SOURCE_TYPE, 1)
                }
            }
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentListDepartment).addToBackStack(null).commit()
        }
    }
}
