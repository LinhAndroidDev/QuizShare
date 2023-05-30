package com.example.appthitracnghiem.ui.home.history.test.topic

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer
import com.example.appthitracnghiem.ui.home.history.test.FragmentHistoryExam
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_history_topic.*

@Suppress("DEPRECATION")
class FragmentHistoryTopic : BaseFragment<HistoryTopicViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                val fragmentAnswer = FragmentAnswer()
                val bundle = Bundle()
                bundle.putString("title", "Lịch sử thi")
                val fm: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                fm.add(R.id.changeIdTopicHistory, fragmentAnswer)
                    .addToBackStack(null).commit()
                fragmentAnswer.arguments = bundle
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

        backTopicHistory.setOnClickListener {
            activity?.onBackPressed()
        }

        seeAgainHistory.setOnClickListener {
            val header = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION, "").toString()
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val examHistoryId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.EXAM_ID_HISTORY, -1)
            viewModel.getIdExam(header, userId, examHistoryId)
        }
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_topic, container, false)
    }
}