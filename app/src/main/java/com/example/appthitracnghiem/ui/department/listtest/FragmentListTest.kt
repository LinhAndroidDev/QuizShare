package com.example.appthitracnghiem.ui.department.listtest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentListTestBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentListTest : BaseFragment<ListTestViewModel>() {
    private var _binding: FragmentListTestBinding? = null
    private val binding get() = _binding!!
    lateinit var testAdapter: TestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle = requireArguments()
        val id: Int = bundle.getInt("ID")
        val title: String = bundle.getString("title").toString()
        binding.textNatural.text = title

        val type = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TYPE, 5)
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)

        // Adapter must exist before initUi: restoreViewState can fire TextWatcher before API returns.
        testAdapter = TestAdapter(requireActivity(), mutableListOf())
        binding.recycleListTest.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recycleListTest.adapter = testAdapter

        viewModel.getListExam(RequestListExam(userId, id, type, 1, "asc"))

        initUi()
    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
        binding.textNatural.typeface = semibold
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingTestLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading && binding.recycleListTest.adapter == null) {
                binding.loadingTest.visibility = View.VISIBLE
            } else {
                binding.loadingTest.visibility = View.INVISIBLE
            }
        }

        viewModel.listTestLiveData.observe(viewLifecycleOwner) {
            testAdapter = TestAdapter(requireActivity(), it)
            binding.recycleListTest.adapter = testAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initUi() {
        binding.layoutListTestCover.setOnTouchListener { view, _ ->
            view.hideKeyboard()
            false
        }

        binding.backTest.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.menuListTest.setOnClickListener {
            showMenuCreate(binding.menuListTest,R.layout.popup_list_test,0,0,Gravity.BOTTOM)
        }

        binding.searchTest.addTextChangedListener(object : TextWatcher {
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
    ): View {
        _binding = FragmentListTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}