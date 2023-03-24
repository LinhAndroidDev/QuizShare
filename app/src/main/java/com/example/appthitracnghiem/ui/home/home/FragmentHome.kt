package com.example.appthitracnghiem.ui.home.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.base.animation.TranslateAnimation
import com.example.appthitracnghiem.ui.home.home.adapter.QuizAdapter
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : BaseFragment<EmptyViewModel>() {
    lateinit var viewModelGeneral: ViewModelGeneral

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (bottomWrap != null) {
            scrollHome.setOnTouchListener(TranslateAnimation(requireActivity(), bottomWrap))
        }

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]
        viewModelGeneral.getDataQuiz()

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recycleListLiveQuizzes.layoutManager = linearLayoutManager

        getData()

        getLoading()

        setText()
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textHome.typeface = semibold
        textQuizHome.typeface = semibold
    }

    private fun getLoading() {
        viewModelGeneral.loadingQuizLive.observe(requireActivity(), Observer { loadingQuizLive ->
            if (loadingQuizLive != null && loadingQuiz != null) {
                if (loadingQuizLive == true) {
                    loadingQuiz.visibility = View.VISIBLE
                } else if (loadingQuizLive == false) {
                    loadingQuiz.visibility = View.INVISIBLE
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        viewModelGeneral.listQuizLive.observe(requireActivity(), Observer { listQuiz ->
            if (listQuiz != null && recycleListLiveQuizzes != null) {
                val adapterQuiz: QuizAdapter = QuizAdapter(listQuiz, requireActivity())
                recycleListLiveQuizzes.adapter = adapterQuiz
                adapterQuiz.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}