package com.example.appthitracnghiem.ui.home.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.adapter.DepartmentAdapter
import com.example.appthitracnghiem.ui.home.home.adapter.SystemViewModel
import kotlinx.android.synthetic.main.fragment_from_user.*
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSystem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSystem : BaseFragment<SystemViewModel>() {
    lateinit var adapterQuiz: DepartmentAdapter
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]
        viewModelGeneral.getDataQuiz()

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recycleListLiveQuizzes.layoutManager = linearLayoutManager

//        getLoading()

//        getData()

        setText()
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingData.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading){
                loadingDepartment.visibility = View.VISIBLE
            }else{
                loadingDepartment.visibility = View.GONE
            }
        }

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){ listDepart ->
            adapterQuiz = DepartmentAdapter(listDepart,requireActivity())
            recycleListLiveQuizzes.adapter = adapterQuiz
        }

        viewModel.getDataDepartment()
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textQuizHome.typeface = semibold
    }

//    private fun getLoading() {
//        viewModelGeneral.loadingQuizLive.observe(requireActivity(), Observer { loadingQuizLive ->
//            if (loadingQuizLive != null && loadingQuiz != null) {
//                if (loadingQuizLive && recycleListLiveQuizzes.adapter == null) {
//                    loadingQuiz.visibility = View.VISIBLE
//                } else if (!loadingQuizLive) {
//                    loadingQuiz.visibility = View.INVISIBLE
//                }
//            }
//        })
//    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun getData() {
//        viewModelGeneral.listQuizLive.observe(requireActivity(), Observer { listQuiz ->
//            if (listQuiz != null) {
//                adapterQuiz = DepartmentAdapter(listQuiz, requireActivity())
//                recycleListLiveQuizzes.adapter = adapterQuiz
//                adapterQuiz.notifyDataSetChanged()
//            }
//        })
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system, container, false)
    }
}