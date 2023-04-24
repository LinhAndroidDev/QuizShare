package com.example.appthitracnghiem.ui.home.history.saved.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.home.history.saved.FragmentHistoryTestSaved

class HistorySubjectAdapter(val context: Context, val listTest: List<Test>) : RecyclerView.Adapter<HistorySubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistorySubjectAdapter.SubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return SubjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistorySubjectAdapter.SubjectViewHolder, position: Int) {
        val test : Test = listTest[position]
        holder.title.text = test.title
        holder.image.setImageResource(test.image)
        holder.description.text = test.description

        holder.itemView.setOnClickListener {
            val activity = context as AppCompatActivity
            val fragmentHistoryTestSaved: FragmentHistoryTestSaved = FragmentHistoryTestSaved()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryTestSaved).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }
}