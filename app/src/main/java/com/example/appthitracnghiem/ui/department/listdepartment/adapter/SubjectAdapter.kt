package com.example.appthitracnghiem.ui.department.listdepartment.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.ui.department.DepartmentNavExtras
import com.example.appthitracnghiem.ui.department.listtest.FragmentListTest
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage

class SubjectAdapter(
    val context: Context,
    private val listSubject: List<Subject>,
    private val listSourceType: Int,
) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolderSubject>() {

    class ViewHolderSubject(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.topic)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolderSubject {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return ViewHolderSubject(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderSubject, position: Int) {
        val subject: Subject = listSubject[position]
        holder.image.loadNetworkImage(subject.image)
        holder.title.text = subject.title
        holder.description.text = subject.description

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("ID", subject.id)
                putString("title", subject.title)
                putInt(DepartmentNavExtras.ARG_LIST_SOURCE_TYPE, listSourceType)
            }
            val activity = context.findAppCompatActivity() ?: return@setOnClickListener
            val fragmentListTest = FragmentListTest()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentListTest).addToBackStack(null).commit()
            fragmentListTest.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }
}