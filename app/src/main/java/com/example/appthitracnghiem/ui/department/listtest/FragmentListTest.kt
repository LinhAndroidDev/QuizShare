package com.example.appthitracnghiem.ui.department.listtest

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_list_test.*

@Suppress("DEPRECATION")
class FragmentListTest : BaseFragment<ListTestViewModel>() {
    lateinit var testAdapter: TestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle = requireArguments()
        val id: Int = bundle.getInt("ID")
        val title: String = bundle.getString("title").toString()
        textNatural.text = title

        val type = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TYPE, 5)

        val accessToken = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION, "").toString()
        val user_id = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)

        val requestListExam = RequestListExam(user_id, id, type, 1, "asc")
        viewModel.getListExam(accessToken, requestListExam)

        initUi()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
        textNatural.typeface = semibold
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingTestLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading && recycleListTest.adapter == null) {
                loadingTest.visibility = View.VISIBLE
            } else {
                loadingTest.visibility = View.INVISIBLE
            }
        }

        viewModel.listTestLiveData.observe(viewLifecycleOwner) {
            testAdapter = TestAdapter(requireActivity(), it)
            val linear =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recycleListTest.layoutManager = linear
            recycleListTest.adapter = testAdapter
        }
    }

    private fun initUi() {
        backTest.setOnClickListener {
            activity?.onBackPressed()
        }

        menuListTest.setOnClickListener {
            showMenuCreate(menuListTest,R.layout.popup_list_test,0,0,Gravity.BOTTOM)
        }

        searchTest.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                testAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        setText()
    }

    private fun showMenuCreate(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_test, container, false)
    }
}