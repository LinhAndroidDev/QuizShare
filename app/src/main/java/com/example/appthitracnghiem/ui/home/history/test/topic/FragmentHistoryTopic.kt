package com.example.appthitracnghiem.ui.home.history.test.topic

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentHistoryTopicBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentHistoryTopic : BaseFragment<HistoryTopicViewModel>() {
    private var _binding: FragmentHistoryTopicBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG_HISTORY_ANSWER = "FragmentHistoryTopic_answer"
    }

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

        viewModel.navigateToAnswerExamIdLiveData.observe(viewLifecycleOwner) { examId ->
            val fm = requireActivity().supportFragmentManager
            if (fm.isStateSaved) return@observe
            if (fm.findFragmentByTag(TAG_HISTORY_ANSWER) != null) return@observe

            val fragmentAnswer = FragmentAnswer()
            val bundle = Bundle().apply {
                putString("title", "Lịch sử thi")
                putInt(ExamSessionExtras.ARG_EXAM_ID, examId)
            }
            fragmentAnswer.arguments = bundle
            fm.beginTransaction()
                .add(R.id.changeIdTopicHistory, fragmentAnswer, TAG_HISTORY_ANSWER)
                .addToBackStack(null)
                .commit()
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

        binding.backTopicHistory.setOnClickListener {
            requireActivity().finish()
        }

        binding.seeAgainHistory.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val examHistoryId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.EXAM_ID_HISTORY, -1)
            viewModel.getIdExam(userId, examHistoryId)
        }
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}