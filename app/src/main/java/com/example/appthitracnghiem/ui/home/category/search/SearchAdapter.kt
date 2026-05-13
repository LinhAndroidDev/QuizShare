package com.example.appthitracnghiem.ui.home.category.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.entity.SearchResponse
import com.example.appthitracnghiem.databinding.LayoutGeneralBinding
import com.example.appthitracnghiem.utils.loadNetworkImage

class SearchAdapter(
    val context: Context,
    private val listSearch: ArrayList<SearchResponse.Results>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(val binding: LayoutGeneralBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val binding = LayoutGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val search: SearchResponse.Results = listSearch[position]
        holder.binding.topic.text = search.title
        val imagePath = search.image?.trim().orEmpty()
        val imageUrl = if (imagePath.isNotEmpty()) {
            "https://storage.googleapis.com/quiz-app-storage/subject/$imagePath"
        } else {
            ""
        }
        holder.binding.image.loadNetworkImage(imageUrl)
        holder.binding.detail.text = context.getString(
            R.string.format_subject_exam_line,
            search.description,
            search.count_exam,
        )

//        holder.binding.root.setOnClickListener {
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
