package com.example.appthitracnghiem.ui.exercise.exercise.point

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestPoint
import com.example.appthitracnghiem.databinding.FragmentPointBinding
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.ExamActivity
import com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPoint : BaseFragment<PointViewModel>() {
    private var _binding: FragmentPointBinding? = null
    private val binding get() = _binding!!

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    private var lastExamHistoryId: Int = 0

    /** Snapshot đáp án do [FragmentExam] truyền bundle (không đọc [ExamViewModel]). */
    private fun answerOptionIndicesFromArgs(): ArrayList<Int> {
        val list = requireArguments().getIntegerArrayList(ExamSessionExtras.ARG_ANSWER_OPTION_INDICES)
        return if (list != null) ArrayList(list) else ArrayList()
    }

    /** Đồng bộ độ dài với số câu hỏi (tránh lệch nếu snapshot rỗng). */
    private fun paddedExamAnswers(): ArrayList<Int> {
        val raw = answerOptionIndicesFromArgs()
        val n = if (::listExamQuestion.isInitialized) listExamQuestion.size else raw.size
        val out = ArrayList(raw)
        while (out.size < n) {
            out.add(-1)
        }
        return out
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun bindData() {
        super.bindData()

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val examId = requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0)

        val bundle: Bundle = requireArguments()
        listExamQuestion = bundle.getSerializable("listExamQuestion") as ArrayList<ExamQuestion>
        val listAnswer = paddedExamAnswers()
        val answerList = HashMap<String,Int?>()

        for(i in 0 until listAnswer.size){
            val value = listAnswer[i]
            if(value == -1){
                answerList[(i+1).toString()] = -1
            }else{
                answerList[(i+1).toString()] = listExamQuestion[i].answer_list[listAnswer[i]].answer_id
            }
        }

        val startTime = requireArguments().getString(ExamSessionExtras.ARG_EXAM_START_TIMESTAMP).orEmpty()

        val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        val finishTime = sdf.format(Date()).toString()

        val requestPoint = RequestPoint(userId, examId, answerList,startTime, finishTime)

        viewModel.getResult(requestPoint)

        viewModel.scoreLiveData.observe(viewLifecycleOwner){ score ->
            score?.let {
                binding.progressPoint.apply {
                    progressMax = 100f
                    setProgressWithAnimation(it,3000)
                }

                binding.txtPoint.text = it.toInt().toString()
                binding.notifiPoint.text = getString(R.string.format_points_earned, it.toInt())
                binding.completePercent.text = getString(R.string.format_percent_int, it.toInt())
            }
        }

        viewModel.numberCorrectLiveData.observe(viewLifecycleOwner) { n ->
            binding.numberCorrect.text = getString(R.string.format_question_count, n ?: 0)
        }

        viewModel.wrongNumberLiveData.observe(viewLifecycleOwner) { n ->
            binding.wrongNumber.text = (n ?: 0).toString()
        }

        viewModel.examIdHistory.observe(viewLifecycleOwner) { id ->
            id?.let { if (it > 0) lastExamHistoryId = it }
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                (activity as ExamActivity).loadingVisible(true)
            }else{
                (activity as ExamActivity).loadingVisible(false)
            }
        }
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun initUi() {
        var count = 0
        val listAnswer = paddedExamAnswers()
        for (i in 0 until listAnswer.size){
            if(listAnswer[i] < 0){
                count++
            }
        }
        binding.skipNumber.text = count.toString()

        setStatusBar()

        val lineSeries = LineGraphSeries(
            arrayOf(
                DataPoint(0.0, 1.5),
                DataPoint(1.8, 1.5),
                DataPoint(2.5, 0.8),
                DataPoint(4.0, 2.3),
                DataPoint(5.0, 2.3),
                DataPoint(5.3, 1.5),
                DataPoint(6.5, 1.5),
                DataPoint(7.3, 2.3),
                DataPoint(8.1, 1.5),
                DataPoint(9.2, 1.5),
                DataPoint(9.2, 1.5),
                DataPoint(10.0, 0.7)
            )
        )
        binding.graphView.addSeries(lineSeries)

        binding.backPoint.setOnClickListener {
            activity?.finish()
        }

        binding.doAgainTest.setOnClickListener {
            activity?.finish()
        }

        binding.finishAndSaved.setOnClickListener {
            val intent = Intent(requireActivity(),HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.seeAnswer.setOnClickListener {
            val fragmentAnswer = FragmentAnswer()
            val bundle = Bundle().apply {
                putString("title", getString(R.string.txtAnswer))
                putInt(
                    ExamSessionExtras.ARG_EXAM_ID,
                    requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0),
                )
                putInt(ExamSessionExtras.ARG_EXAM_HISTORY_ID, lastExamHistoryId)
            }
            fragmentAnswer.arguments = bundle
            val fm: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.replace(R.id.changeIdExam, fragmentAnswer).addToBackStack(null).commit()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}