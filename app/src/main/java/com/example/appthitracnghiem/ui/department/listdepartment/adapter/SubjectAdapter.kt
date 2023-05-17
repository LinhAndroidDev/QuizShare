package com.example.appthitracnghiem.ui.department.listdepartment.adapter

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
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.ui.department.listtest.FragmentListTest
import com.squareup.picasso.Picasso

class SubjectAdapter(val context: Context, private val listSubject: List<Subject>) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolderSubject>() {

    class ViewHolderSubject(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.topic)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SubjectAdapter.ViewHolderSubject {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return ViewHolderSubject(itemView)
    }

    override fun onBindViewHolder(holder: SubjectAdapter.ViewHolderSubject, position: Int) {
        val subject: Subject = listSubject[position]
        Picasso.get().load(subject.image)
            .error(R.drawable.errorimage)
            .placeholder(R.drawable.loadimage)
            .into(holder.image)
        holder.title.text = subject.title
        holder.description.text = subject.description

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("ID", subject.id)
            bundle.putString("title", subject.title)
            val activity = context as AppCompatActivity
            val fragmentListTest = FragmentListTest()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_up_enter,
                R.anim.anim_ignored_out,
                R.anim.anim_ignored_in,
                R.anim.anim_down_enter
            )
            fm.add(R.id.changeIdHome, fragmentListTest).addToBackStack(null).commit()
            fragmentListTest.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }
}