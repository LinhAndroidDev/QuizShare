package com.example.appthitracnghiem.ui.department.listtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.exercise.ExerciseActivity

class TestAdapter(val context: Context, private var listTest: MutableList<Test>) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>(), Filterable{

    var listTestOld: MutableList<Test> = listTest

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
        val test: Test = listTest[position]
        holder.image.setImageResource(test.image)
        holder.title.text = test.title
        holder.description.text = test.description

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, ExerciseActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTest.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(strTxt: CharSequence?): FilterResults {
                val txtString: String = strTxt.toString().lowercase().trim()

                if(txtString.isEmpty()){
                    listTest = listTestOld
                }else{
                    val list: MutableList<Test> = mutableListOf()
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
                    listTest = p1.values as MutableList<Test>
                    notifyDataSetChanged()
                }
            }

        }
    }
}