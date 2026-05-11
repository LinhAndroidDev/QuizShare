package com.example.appthitracnghiem.ui.home.category

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentCategoryBinding
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo
import com.example.appthitracnghiem.ui.home.category.adapter.SubjectAdapter
import com.example.appthitracnghiem.ui.home.category.search.SearchSubject
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentCategory : BaseFragment<ListDepartmentViewModel>() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    lateinit var listCategory: ArrayList<Subject>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun bindData() {
        super.bindData()

        listCategory = arrayListOf()

        viewModel.loadingDepartmentLiveData.observe(viewLifecycleOwner){
            if(it){
                binding.loadingSubject.visibility = View.VISIBLE
            }else{
                binding.loadingSubject.visibility = View.GONE
            }
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        viewModel.getDataDepartmentDetail(RequestDepartmentInfo(userId))

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){
            for(i in 0 until  it.size){
                for (j in 0 until  it[i].subjects.size){
                    listCategory.add(it[i].subjects[j])
                }
            }
            if (_binding != null) {
                val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
                binding.recycleListSubject.layoutManager = gridLayoutManager
                val subjectAdapter = SubjectAdapter(listCategory, requireActivity())
                binding.recycleListSubject.adapter = subjectAdapter
                subjectAdapter.notifyDataSetChanged()
            }
        }
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

        binding.backCategory.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.searchCategory.setOnClickListener {
            val intent = Intent(requireActivity(), SearchSubject::class.java)
            startActivity(intent)
        }
    }

    internal fun scrollTop(){
        binding.scrollCategory.post {
            binding.scrollCategory.fling(0)
            binding.scrollCategory.smoothScrollTo(0, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
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