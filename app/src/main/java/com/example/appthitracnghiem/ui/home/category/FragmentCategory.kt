package com.example.appthitracnghiem.ui.home.category

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.category.adapter.SubjectAdapter
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.category.search.SearchSubject
import kotlinx.android.synthetic.main.fragment_category.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCategory.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCategory : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backCategory.setOnClickListener {
            requireActivity().onBackPressed()
        }

        searchCategory.setOnClickListener {
            var intent: Intent = Intent(requireActivity(), SearchSubject::class.java)
            startActivity(intent)
        }

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        viewModelGeneral.getListSubject()

        var gridLayoutManager: GridLayoutManager = GridLayoutManager(requireActivity(), 2)
        recycleListSubject.layoutManager = gridLayoutManager

        getData()

        getLoading()

        setText()

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
                    if (loadingSubjectLive == false) {
                        loadingSubject.visibility = View.INVISIBLE
                    } else if (loadingSubjectLive == true) {
                        loadingSubject.visibility = View.VISIBLE
                    }
                }
            })
    }

    private fun getData() {
        viewModelGeneral.listSubjectLive.observe(requireActivity(), Observer { listSubject ->
            if (listSubject != null && recycleListSubject != null) {
                var subjectAdapter: SubjectAdapter = SubjectAdapter(listSubject, requireActivity())
                recycleListSubject.adapter = subjectAdapter
                subjectAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCategory.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCategory().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}