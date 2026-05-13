package com.example.appthitracnghiem.ui.department.listdepartment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.databinding.LayoutDetailDepartmentBinding
import com.example.appthitracnghiem.model.DetailDepartment
import com.example.appthitracnghiem.model.Subject

class ListDepartmentAdapter(
    val context: Context,
    initialList: MutableList<DetailDepartment>,
    private val listSourceType: Int,
) : RecyclerView.Adapter<ListDepartmentAdapter.ViewholderDepartment>(), Filterable {

    /** Full tree (departments + all subjects) used when clearing search or re-filtering. */
    private var fullList: MutableList<DetailDepartment> = snapshotDepartments(initialList)

    /** What is currently shown (may reflect subject search). */
    var listDepartment: MutableList<DetailDepartment> = snapshotDepartments(fullList)
        private set

    class ViewholderDepartment(val binding: LayoutDetailDepartmentBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(departments: MutableList<DetailDepartment>) {
        fullList = snapshotDepartments(departments)
        listDepartment = snapshotDepartments(fullList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewholderDepartment {
        val binding = LayoutDetailDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewholderDepartment(binding)
    }

    override fun onBindViewHolder(
        holder: ViewholderDepartment,
        position: Int,
    ) {
        val detailDepartment: DetailDepartment = listDepartment[position]
        holder.binding.txtNameDepartment.text = detailDepartment.title
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        holder.binding.listSubjectDepartment.layoutManager = linearLayoutManager

        val subjectAdapter = SubjectAdapter(holder.binding.root.context, detailDepartment.subjects, listSourceType)
        holder.binding.listSubjectDepartment.adapter = subjectAdapter
    }

    override fun getItemCount(): Int = listDepartment.size

    override fun getFilter(): Filter = SubjectInDepartmentFilter()

    private inner class SubjectInDepartmentFilter : Filter() {
        override fun performFiltering(strTxt: CharSequence?): FilterResults {
            val q = strTxt.toString().trim().lowercase()
            val resultList: MutableList<DetailDepartment> = if (q.isEmpty()) {
                snapshotDepartments(fullList)
            } else {
                fullList.mapNotNull { dept ->
                    val matched = dept.subjects.filter { sub ->
                        sub.title.lowercase().contains(q) ||
                                sub.description.lowercase().contains(q)
                    }
                    if (matched.isEmpty()) null
                    else DetailDepartment(dept.id, dept.exam_num, dept.title, matched)
                }.toMutableList()
            }
            return FilterResults().apply { values = resultList }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(strTxt: CharSequence?, filterResults: FilterResults?) {
            @Suppress("UNCHECKED_CAST")
            val values = filterResults?.values as? MutableList<DetailDepartment> ?: return
            listDepartment = values
            notifyDataSetChanged()
        }
    }

    companion object {
        private fun snapshotDepartments(source: MutableList<DetailDepartment>): MutableList<DetailDepartment> =
            source.map { dept ->
                DetailDepartment(
                    dept.id,
                    dept.exam_num,
                    dept.title,
                    dept.subjects.map { sub ->
                        Subject(sub.id, sub.image, sub.title, sub.description)
                    },
                )
            }.toMutableList()
    }
}
