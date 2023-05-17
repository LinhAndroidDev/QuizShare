package com.example.appthitracnghiem.ui.home.category.search

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
import com.example.appthitracnghiem.data.remote.entity.SearchResponse
import com.example.appthitracnghiem.ui.department.listtest.FragmentListTest
import com.squareup.picasso.Picasso

class SearchAdapter(
    val context: Context,
    private val listSearch: ArrayList<SearchResponse.Results>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.topic)
        var image: ImageView = itemView.findViewById(R.id.image)
        var description: TextView = itemView.findViewById(R.id.detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_general, parent,false)
        return SearchViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val search: SearchResponse.Results = listSearch[position]
        holder.title.text = search.title
        Picasso.get().load("https://storage.googleapis.com/quiz-app-storage/subject/"+search.image)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.image)
        holder.description.text = search.description + " • " + search.count_exam + " đề"

//        holder.itemView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putInt("ID", search.id)
//            bundle.putString("title", search.title)
//            val activity = context as AppCompatActivity
//            val fragmentListTest = FragmentListTest()
//            val fm: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
//            fm.setCustomAnimations(
//                R.anim.anim_up_enter,
//                R.anim.anim_ignored_out,
//                R.anim.anim_ignored_in,
//                R.anim.anim_down_enter
//            )
//            fm.add(R.id.changeIdHome, fragmentListTest).addToBackStack(null).commit()
//            fragmentListTest.arguments = bundle
//        }
    }

    override fun getItemCount(): Int {
        return listSearch.size
    }
}