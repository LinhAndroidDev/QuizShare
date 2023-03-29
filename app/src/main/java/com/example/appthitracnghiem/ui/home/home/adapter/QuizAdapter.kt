package com.example.appthitracnghiem.ui.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Quiz
import com.example.appthitracnghiem.ui.department.listdepartment.FragmentListDepartment
import com.squareup.picasso.Picasso

class QuizAdapter(val listQuiz: List<Quiz>, val context: Context) :
    RecyclerView.Adapter<QuizAdapter.ViewHolderQuiz>() {
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
        val quiz: Quiz = listQuiz[position]
        Picasso.get().load(quiz.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.title.text = quiz.topic
        holder.description.text = quiz.detail

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val fragmentListDepartment : FragmentListDepartment = FragmentListDepartment()
                val fm : FragmentTransaction = activity.supportFragmentManager.beginTransaction()
                fm.replace(R.id.changeIdHome,fragmentListDepartment).addToBackStack(null).commit()
            }

        })
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}