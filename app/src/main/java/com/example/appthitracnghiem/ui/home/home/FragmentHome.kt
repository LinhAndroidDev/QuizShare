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
import com.example.appthitracnghiem.ui.home.home.adapter.QuizAdapter
import com.example.appthitracnghiem.ui.base.animation.TranslateAnimation
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}