package com.example.appthitracnghiem.ui.home.createtest.question.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_create_exam.*
import java.lang.reflect.Type

class PositiveQuestionAdapter(private val max: Int, val context: Context) :
    RecyclerView.Adapter<PositiveQuestionAdapter.PositiveViewHolder>() {
    private var selectedIndex: Int = 0
    var onClickItem: ((Int)->Unit)? = null

    class PositiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtPositive: TextView = itemView.findViewById(R.id.txtPositive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositiveViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_position_question, parent, false)
        return PositiveViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: PositiveViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.txtPositive.text = (position + 1).toString()

        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
            onClickItem?.invoke(position)
        }

        if (selectedIndex == position) {
            holder.txtPositive.setTextColor(Color.WHITE)
            holder.txtPositive.setBackgroundResource(R.drawable.select_positive_quiz)
        } else {
            val t = getListPositive(PreferenceKey.LIST_CREATE_NUMBER_QUESTION)
            getListPositive(PreferenceKey.LIST_CREATE_NUMBER_QUESTION).let {
                if(it[position] == -2){
                    holder.txtPositive.setTextColor(Color.BLACK)
                }else if(it[position] == -1){
                    holder.txtPositive.setTextColor(ContextCompat.getColor(context,R.color.pink_red))
                }else{
                    holder.txtPositive.setTextColor(ContextCompat.getColor(context,R.color.backgroundIntro))
                }
            }
            holder.txtPositive.setBackgroundResource(R.drawable.un_select_positive_quiz)
        }
    }

    private fun getListPositive(key: String?): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    override fun getItemCount(): Int {
        return max
    }

    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        this.notifyDataSetChanged()
    }
}