package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.exam.FragmentExam
import kotlinx.android.synthetic.main.layout_menu_question.view.*

@Suppress("DEPRECATION")
class MenuQuestionAdapter(val context: Context, private val listQuestion: List<PositiveQuestion>)
    : RecyclerView.Adapter<MenuQuestionAdapter.ViewHolderQuestion>() {

    // Todo; callback dua value duoc click ra ben ngoai - popupWindow
    var onClickItem: ((Int) -> Unit)? = null
    var positionSelect: Int = -1

    class ViewHolderQuestion(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtQuestion: TextView = itemView.findViewById(R.id.txtQuestion)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MenuQuestionAdapter.ViewHolderQuestion {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_question,parent,false)
        return ViewHolderQuestion(itemView)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MenuQuestionAdapter.ViewHolderQuestion, position: Int) {
        val positiveQuestion: PositiveQuestion = listQuestion[position]
        holder.txtQuestion.text = positiveQuestion.number.toString()

        holder.itemView.setOnClickListener {
            positionSelect = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        val activity = context as AppCompatActivity
        val fm = activity.supportFragmentManager.findFragmentById(R.id.changeIdExam)
        if(fm is FragmentExam){
            fm.onClickNextQuestion = {
                val ps: PositiveQuestion = listQuestion[it]
                holder.txtQuestion.text = ps.number.toString()
                holder.txtQuestion.setBackgroundResource(R.drawable.selected_sentence)
                holder.txtQuestion.setTextColor(Color.WHITE)
            }
        }

        if(positionSelect == position){
            holder.txtQuestion.setBackgroundResource(R.drawable.selected_sentence)
            holder.txtQuestion.setTextColor(Color.WHITE)
        }else{
            holder.txtQuestion.setBackgroundResource(R.drawable.boder_question)
            holder.txtQuestion.setTextColor(ContextCompat.getColor(context,R.color.backgroundIntro))
        }
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}