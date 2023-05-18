package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.preference.PreferenceManager
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
import com.example.appthitracnghiem.ui.exercise.exercise.exam.FragmentExam.Companion.listQuestion
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.layout_menu_question.view.*
import java.lang.reflect.Type

@Suppress("DEPRECATION")
class MenuQuestionAdapter(val context: Context, private val listQuestion: List<PositiveQuestion>)
    : RecyclerView.Adapter<MenuQuestionAdapter.ViewHolderQuestion>() {

    // Todo; callback dua value duoc click ra ben ngoai - popupWindow
    var onClickItem: ((Int) -> Unit)? = null

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
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        val arrAnswer: ArrayList<Int> = getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
        if(arrAnswer[position] == -1){
            positiveQuestion.isSelect = false
        }else if(arrAnswer[position] > -1){
            positiveQuestion.isSelect = true
        }

        if (positiveQuestion.isSelect == true){
            holder.txtQuestion.setBackgroundResource(R.drawable.selected_sentence)
            holder.txtQuestion.setTextColor(Color.WHITE)
        }else if(positiveQuestion.isSelect == false){
            holder.txtQuestion.setBackgroundResource(R.drawable.un_selected_sentence)
            holder.txtQuestion.setTextColor(ContextCompat.getColor(context,R.color.backgroundIntro))
        }
    }

    private fun getListAnswer(key: String?): ArrayList<Int> {
        val activity = context as AppCompatActivity
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }
}