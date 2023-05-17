package com.example.appthitracnghiem.ui.home.category.search

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.activity_search_subject.*

@Suppress("DEPRECATION")
class SearchSubject : BaseActivity<SearchViewModel>() {
    lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_subject)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        viewModel.isLoadingLiveData.observe(this, Observer {
            if(it){
                loadingSubject.visibility = View.VISIBLE
            }else{
                loadingSubject.visibility = View.GONE
            }
        })

        viewModel.listSearchLiveData.observe(this, Observer {
            val linear = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            recycleListSubject.layoutManager = linear
            searchAdapter = SearchAdapter(this, it)
            recycleListSubject.adapter = searchAdapter
        })
    }

    private fun setStatusBar() {
        val window: Window? = this.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(this, R.color.backgroundIntro)

        val decorView = window?.decorView //set status background black

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.systemUiVisibility?.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())!!
        } //set status text  light
    }

    private fun initUi() {

        viewModel.mPreferenceUtil.defaultPref()
            .edit().putInt(PreferenceKey.TYPE, 1)
            .apply()

        setStatusBar()

        backSearchSubject.setOnClickListener {
            onBackPressed()
        }

        search.setOnClickListener {
            var strSearch = edtSearchSubject.text.toString()
            if(strSearch.isEmpty()){
                strSearch = ""
            }
            val header = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION,"").toString()
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID,0)
            val requestSearch = RequestSearch(userId, 1, strSearch)
            viewModel.searchSubject(header, requestSearch)
        }
    }
}