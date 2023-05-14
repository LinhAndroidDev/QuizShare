package com.example.appthitracnghiem.ui.home.history.test.general

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.HistoryExam
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_history_test.*
import kotlinx.android.synthetic.main.fragment_list_test.*

@Suppress("DEPRECATION")
class FragmentHistoryTest : BaseFragment<HistoryTestViewModel>() {
    lateinit var testAdapter: HistoryTestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val requestExamHistory = RequestExamHistory(userId,5,0,1,"asc")

        viewModel.getExamHistory(header, requestExamHistory)

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                (activity as HomeActivity).loadingVisible(true)
            }else{
                (activity as HomeActivity).loadingVisible(false)
            }
        }

        viewModel.listExamHistoryLiveData.observe(viewLifecycleOwner){
            val linear = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL, false
            )
            testAdapter = HistoryTestAdapter(requireActivity(),it)
            listTestFromSystem.layoutManager = linear
            listTestFromSystem.adapter = testAdapter
        }
    }

    private fun initUi() {
        backHistoryTest.setOnClickListener {
            activity?.onBackPressed()
        }

        menuHistoryTest.setOnClickListener {
            showPopupMenu(menuHistoryTest, R.layout.popup_history_test, 0, 0, Gravity.BOTTOM)
        }

        setText()
    }

    private fun showPopupMenu(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtTitleHistory.typeface = semibold
        txtFromSystem.typeface = semibold
        txtFromUser.typeface = semibold
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_test, container, false)
    }
}