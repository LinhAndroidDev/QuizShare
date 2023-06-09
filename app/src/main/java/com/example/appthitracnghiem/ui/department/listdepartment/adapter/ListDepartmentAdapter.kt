package com.example.appthitracnghiem.ui.department.listdepartment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.model.Subject
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

class ListDepartmentAdapter(
    val context: Context,
    var listDepartment: MutableList<DetailDepartment>
) :
    RecyclerView.Adapter<ListDepartmentAdapter.ViewholderDepartment>(), Filterable {

    private var listDepartmentOlds: MutableList<DetailDepartment> = listDepartment

    class ViewholderDepartment(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameDepartment: TextView = itemView.findViewById(R.id.txtNameDepartment)
        var txtSeeAll: TextView = itemView.findViewById(R.id.txtSeeAllDepartment)
        var recycleViewListSubjectDepartment: RecyclerView =
            itemView.findViewById(R.id.listSubjectDepartment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListDepartmentAdapter.ViewholderDepartment {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detail_department, parent, false)
        return ViewholderDepartment(itemView)
    }

    override fun onBindViewHolder(
        holder: ListDepartmentAdapter.ViewholderDepartment,
        position: Int,
    ) {
        val detailDepartment: DetailDepartment = listDepartment[position]
        holder.txtNameDepartment.text = detailDepartment.title
        val semibold: Typeface? = ResourcesCompat.getFont(context,R.font.svn_gilroy_semibold)
        holder.txtNameDepartment.typeface = semibold
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        holder.recycleViewListSubjectDepartment.layoutManager = linearLayoutManager

        val subjectAdapter = SubjectAdapter(holder.txtSeeAll.context, detailDepartment.subjects)
        holder.recycleViewListSubjectDepartment.adapter = subjectAdapter
    }

    override fun getItemCount(): Int {
        return listDepartment.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(strTxt: CharSequence?): FilterResults {
                val strSearch: String = strTxt.toString().lowercase()

                listDepartment = if(strSearch.isEmpty()){
                    listDepartmentOlds
                }else{
                    val list: MutableList<DetailDepartment> = mutableListOf()
                    for (department in listDepartmentOlds) {
                        if(department.title.lowercase().contains(strSearch)){
                            list.add(department)
                        }
                    }

                    list
                }

                val filterResults = FilterResults()
                filterResults.values = listDepartment
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(strTxt: CharSequence?, filterResults: FilterResults?) {
                if(filterResults?.values != null){
                    listDepartment = filterResults.values as MutableList<DetailDepartment>
                    notifyDataSetChanged()
                }
            }

        }
    }
}