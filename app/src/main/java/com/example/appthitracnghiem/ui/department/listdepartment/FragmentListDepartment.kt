package com.example.appthitracnghiem.ui.department.listdepartment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentListDepartmentBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentListDepartment : BaseFragment<ListDepartmentViewModel>() {
    private var _binding: FragmentListDepartmentBinding? = null
    private val binding get() = _binding!!
    lateinit var listDepartmentAdapter: ListDepartmentAdapter
    var userId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingDepartmentLiveData.observe(viewLifecycleOwner){ isLoading->
            if(isLoading && binding.recycleDetailListDepartment.adapter == null){
                binding.loadingDepartmentInfo.visibility = View.VISIBLE
            }else{
                binding.loadingDepartmentInfo.visibility = View.INVISIBLE
            }
        }

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner) { apiDepartments ->
            val selectedId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.ID_DEPARTMENT, -1)
            val displayList = if (selectedId > 0) {
                apiDepartments.filter { it.id == selectedId }.toMutableList()
            } else {
                apiDepartments.toMutableList()
            }

            if (!::listDepartmentAdapter.isInitialized) {
                listDepartmentAdapter = ListDepartmentAdapter(requireActivity(), displayList)
                val linear = LinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                binding.recycleDetailListDepartment.layoutManager = linear
                binding.recycleDetailListDepartment.adapter = listDepartmentAdapter
            } else {
                listDepartmentAdapter.replaceAll(displayList)
            }
            binding.searchDepartment.setText("")
        }

        userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0)
        viewModel.getDataDepartmentDetail(RequestDepartmentInfo(userId))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initUi() {
        binding.layoutListDepartmentCover.setOnTouchListener { view, _ ->
            view.hideKeyboard()
            false
        }

        binding.backDepartment.setOnClickListener{
            activity?.onBackPressed()
        }

        binding.searchDepartment.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (::listDepartmentAdapter.isInitialized) {
                    listDepartmentAdapter.filter.filter(p0)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListDepartmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}