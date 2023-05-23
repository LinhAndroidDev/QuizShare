package com.example.appthitracnghiem.ui.home.createtest.question.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PositiveQuestionAdapter(val listPossitive: List<PositiveQuestion>, val context: Context) :
    RecyclerView.Adapter<PositiveQuestionAdapter.PositiveViewHolder>() {
    var number: Int = -1
    var getPositiveQuestion: ((Int) -> Unit)? = null

    class PositiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtPositive: TextView = itemView.findViewById(R.id.txtPositive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositiveViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_position_question, parent, false)
        return PositiveViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PositiveViewHolder, position: Int) {
        val positiveQuestion: PositiveQuestion = listPossitive[position]
        holder.txtPositive.text = positiveQuestion.number.toString()

        holder.itemView.setOnClickListener {
            number = position
            notifyDataSetChanged()
            getPositiveQuestion?.invoke(position)
        }

        if(positiveQuestion.isSelect == true){
            holder.txtPositive.setTextColor(Color.WHITE)
            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
        }else if(positiveQuestion.isSelect == false){
            holder.txtPositive.setTextColor(Color.BLACK)
            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }

//        if (number == position) {
//            holder.txtPositive.setTextColor(Color.WHITE)
//            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
//        } else {
//            holder.txtPositive.setTextColor(Color.BLACK)
//            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
//            if(positiveQuestion.isSelect == true){
//                holder.txtPositive.setTextColor(Color.WHITE)
//                holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
//            }
//        }
    }

    override fun getItemCount(): Int {
        return listPossitive.size
    }
}