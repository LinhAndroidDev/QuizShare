package com.example.appthitracnghiem.ui.home.category

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Subject
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo
import com.example.appthitracnghiem.ui.home.category.adapter.SubjectAdapter
import com.example.appthitracnghiem.ui.home.category.search.SearchSubject
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_category.*

@Suppress("DEPRECATION")
class FragmentCategory : BaseFragment<ListDepartmentViewModel>() {
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
                loadingSubject.visibility = View.VISIBLE
            }else{
                loadingSubject.visibility = View.GONE
            }
        }

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION, "").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val requestDepartmentInfo = RequestDepartmentInfo(userId)
        viewModel.getDataDepartmentDetail(header, requestDepartmentInfo)

        viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){
            for(i in 0 until  it.size){
                for (j in 0 until  it[i].subjects.size){
                    listCategory.add(it[i].subjects[j])
                }
            }
            if (recycleListSubject != null) {
                val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
                recycleListSubject.layoutManager = gridLayoutManager
                val subjectAdapter = SubjectAdapter(listCategory, requireActivity())
                recycleListSubject.adapter = subjectAdapter
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