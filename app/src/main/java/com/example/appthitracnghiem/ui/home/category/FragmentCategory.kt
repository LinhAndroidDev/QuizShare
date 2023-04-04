package com.example.appthitracnghiem.ui.home.category

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.category.adapter.SubjectAdapter
import com.example.appthitracnghiem.ui.home.category.search.SearchSubject
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCategory.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCategory : BaseFragment<EmptyViewModel>() {
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        viewModelGeneral.getListSubject()

        val gridLayoutManager: GridLayoutManager = GridLayoutManager(requireActivity(), 2)
        recycleListSubject.layoutManager = gridLayoutManager

        getData()

        getLoading()

        setText()

        click()
    }

    private fun click() {
        backCategory.setOnClickListener {
            requireActivity().onBackPressed()
        }

        searchCategory.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), SearchSubject::class.java)
            startActivity(intent)
        }
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textSubject.typeface = semibold
    }

    private fun getLoading() {
        viewModelGeneral.loadingSubjectLive.observe(
            requireActivity(),
            Observer { loadingSubjectLive ->
                if (loadingSubjectLive != null && loadingSubject != null) {
                    if (!loadingSubjectLive) {
                        loadingSubject.visibility = View.INVISIBLE
                    } else if (loadingSubjectLive && recycleListSubject.adapter == null) {
                        loadingSubject.visibility = View.VISIBLE
                    }
                }
            })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        viewModelGeneral.listSubjectLive.observe(requireActivity(), Observer { listSubject ->
            if (listSubject != null && recycleListSubject != null) {
                val subjectAdapter: SubjectAdapter = SubjectAdapter(listSubject, requireActivity())
                recycleListSubject.adapter = subjectAdapter
                subjectAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}