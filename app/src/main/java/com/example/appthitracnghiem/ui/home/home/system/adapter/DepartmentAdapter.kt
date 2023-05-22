package com.example.appthitracnghiem.ui.home.home.system.adapter

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
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import com.squareup.picasso.Picasso

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
        Picasso.get().load(quiz.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.title.text = quiz.title
        holder.description.text = quiz.description

        holder.itemView.setOnClickListener { v ->
            val activity = v?.context as AppCompatActivity
            val mPreferenceUtil = PreferenceUtil(activity)
            mPreferenceUtil.defaultPref().edit()
                .putInt(PreferenceKey.TYPE, 0)
                .apply()
            mPreferenceUtil.defaultPref().edit()
                .putInt(PreferenceKey.ID_DEPARTMENT,position)
                .apply()
            val fragmentListDepartment = FragmentListDepartment()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_up_enter,
                R.anim.anim_ignored_out,
                R.anim.anim_ignored_in,
                R.anim.anim_down_enter
            )
            fm.add(R.id.changeIdHome, fragmentListDepartment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}