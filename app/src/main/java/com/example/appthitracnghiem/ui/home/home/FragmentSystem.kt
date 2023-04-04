package com.example.appthitracnghiem.ui.home.home

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.adapter.DepartmentAdapter
import kotlinx.android.synthetic.main.fragment_from_user.*
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSystem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSystem : BaseFragment<SystemViewModel>() {
    private lateinit var adapterQuiz: DepartmentAdapter
    lateinit var viewModelGeneral: ViewModelGeneral
    private var accessToken: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImxpbmhAZ21haWwuY29tIiwiaWQiOjU3LCJleHBpcnlfYXQiOjE2ODExOTgwNTguMjY1MTM4Nn0.TsEHWCgfCW4l9V9XV4wroA2Ry9PwI9XMdyyYX4TUVxg"
    private var user_id: Int = 57

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]
//        viewModelGeneral.getDataQuiz()

        accessToken = requireActivity().intent.getStringExtra("accessToken").toString()
        user_id = requireActivity().intent.getIntExtra("user_id",0)

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

        val requestGetListDepartment = RequestGetListDepartment(user_id,"")
        viewModel.getDataDepartment(accessToken,requestGetListDepartment)
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