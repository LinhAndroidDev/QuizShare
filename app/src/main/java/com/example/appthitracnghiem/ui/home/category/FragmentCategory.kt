package com.example.appthitracnghiem.ui.home.category

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
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

@Suppress("DEPRECATION")
class FragmentCategory : BaseFragment<EmptyViewModel>() {
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        viewModelGeneral.getListSubject()

        val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
        recycleListSubject.layoutManager = gridLayoutManager

        getData()

        getLoading()

        initUi()
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.backgroundIntro)

        val decorView = window?.decorView //set status background black

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.systemUiVisibility?.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())!!
        } //set status text  light
    }

    private fun initUi() {

        setStatusBar()

        backCategory.setOnClickListener {
            requireActivity().onBackPressed()
        }

        searchCategory.setOnClickListener {
            val intent = Intent(requireActivity(), SearchSubject::class.java)
            startActivity(intent)
        }

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