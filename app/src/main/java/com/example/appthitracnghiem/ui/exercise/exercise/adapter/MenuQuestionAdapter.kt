package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.ExamViewModel
import com.example.appthitracnghiem.ui.exercise.exercise.FragmentExam
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import kotlinx.android.synthetic.main.layout_menu_question.view.*
import java.lang.reflect.ParameterizedType

@Suppress("DEPRECATION")
class MenuQuestionAdapter(val context: Context, private val listQuestion: List<PositiveQuestion>)
    : RecyclerView.Adapter<MenuQuestionAdapter.ViewHolderQuestion>() {

    // Todo; callback dua value duoc click ra ben ngoai - popupWindow
    var onClickItem: ((Int) -> Unit)? = null
    var positionComplete: Int = -1

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

    override fun onBindViewHolder(holder: MenuQuestionAdapter.ViewHolderQuestion, position: Int) {
        val positiveQuestion: PositiveQuestion = listQuestion[position]
        holder.txtQuestion.text = positiveQuestion.number.toString()

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(position)
        }

        val fragmentExam: FragmentExam = FragmentExam()
        fragmentExam.onSelectAnswer = { positionAnswerSelect->
            positionComplete = positionAnswerSelect
        }
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}