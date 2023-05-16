package com.example.appthitracnghiem.ui.home.history.saved.subject.adapter

import android.annotation.SuppressLint
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
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.ui.home.history.saved.test.FragmentHistoryTestSaved
import com.squareup.picasso.Picasso

class HistorySubjectAdapter(val context: Context, private val listSubject: ArrayList<SaveSubjectResponse.Result>?) : RecyclerView.Adapter<HistorySubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general,parent,false)
        return SubjectViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val savedSubject : SaveSubjectResponse.Result = listSubject!![position]
        holder.title.text = savedSubject.title
        Picasso.get().load(savedSubject.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.description.text = savedSubject.department_title + " • " +  savedSubject.exem_number.toString() + " đề"

        holder.itemView.setOnClickListener {
            val activity = context as AppCompatActivity
            val bundle = Bundle()
            bundle.putInt("subject_saved_id", savedSubject.id)
            val fragmentHistoryTestSaved = FragmentHistoryTestSaved()
            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fm.add(R.id.changeIdHome,fragmentHistoryTestSaved).addToBackStack(null).commit()
            fragmentHistoryTestSaved.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return listSubject!!.size
    }
}