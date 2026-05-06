package com.example.appthitracnghiem.ui.home.home.user.adapter

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
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.example.appthitracnghiem.ui.home.home.system.FragmentSystem
import com.example.appthitracnghiem.ui.home.home.system.adapter.DepartmentAdapter
import com.example.appthitracnghiem.ui.home.home.user.FragmentFromUser
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import com.example.appthitracnghiem.utils.findAppCompatActivity
import com.example.appthitracnghiem.utils.loadNetworkImage
import androidx.core.content.edit

class FromUserAdapter(
    private val listQuiz: List<Department>,
    val context: Context
    ) : RecyclerView.Adapter<FromUserAdapter.ViewHolderQuiz>(){

    class ViewHolderQuiz(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.topic)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FromUserAdapter.ViewHolderQuiz {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return ViewHolderQuiz(itemView)
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }

    override fun onBindViewHolder(holder: FromUserAdapter.ViewHolderQuiz, position: Int) {
        val quiz: Department = listQuiz[position]
        holder.image.loadNetworkImage(quiz.image)
        holder.title.text = quiz.title
        holder.description.text = quiz.description

        holder.itemView.setOnClickListener { v ->
            val activity = v.context.findAppCompatActivity() ?: return@setOnClickListener
            val mPreferenceUtil = PreferenceUtil(activity)
            mPreferenceUtil.defaultPref().edit()
                .putInt(PreferenceKey.TYPE, 1)
                .apply()
//            val bundle = Bundle()
//            bundle.putString("title", quiz.title)
            val fragmentListDepartment = FragmentListDepartment()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome, fragmentListDepartment).addToBackStack(null).commit()
//            fragmentListDepartment.arguments = bundle
        }
    }
}