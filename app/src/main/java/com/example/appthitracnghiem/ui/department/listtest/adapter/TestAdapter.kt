package com.example.appthitracnghiem.ui.department.listtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Exam
import com.example.appthitracnghiem.ui.exercise.topic.ExerciseActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.PreferenceUtil
import com.squareup.picasso.Picasso

class TestAdapter(val context: Context, private var listTest: MutableList<Exam>) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>(), Filterable{

    var listTestOld: MutableList<Exam> = listTest
    var id_exam: Int = 0
    var time_exam: Int = 0

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.TestViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent, false)
        return TestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestAdapter.TestViewHolder, position: Int) {
        val exam: Exam = listTest[position]
        Picasso.get().load(exam.image)
            .error(R.drawable.errorimage)
            .placeholder(R.drawable.loadimage)
            .into(holder.image)
        holder.title.text = exam.title
        holder.description.text = exam.number.toString() + " Câu trắc nghiệm"

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, ExerciseActivity::class.java)
            context.startActivity(intent)
            saveKey(exam.id,exam.time)
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }

    fun saveKey(id: Int, time: Int){
        val mPreferenceUtil: PreferenceUtil = PreferenceUtil(context)
        mPreferenceUtil.defaultPref().edit()
            .putInt(PreferenceKey.ID_EXAM,id)
            .apply()
        mPreferenceUtil.defaultPref().edit()
            .putInt(PreferenceKey.TIME_EXAM,time)
            .apply()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(strTxt: CharSequence?): FilterResults {
                val txtString: String = strTxt.toString().lowercase().trim()

                if(txtString.isEmpty()){
                    listTest = listTestOld
                }else{
                    val list: MutableList<Exam> = mutableListOf()
                    for(test in listTestOld){
                        if(test.title.lowercase().contains(txtString)){
                            list.add(test)
                        }
                    }

                    listTest = list
                }

                val filterResults: FilterResults = FilterResults()
                filterResults.values = listTest
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(strTxt: CharSequence?, p1: FilterResults?) {
                if(p1?.values != null){
                    listTest = p1.values as MutableList<Exam>
                    notifyDataSetChanged()
                }
            }

        }
    }
}