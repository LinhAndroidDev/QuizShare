package com.example.appthitracnghiem.ui.exercise.exercise.point

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.ExamActivity
import com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_point.*
import kotlinx.android.synthetic.main.fragment_point.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Suppress("DEPRECATION")
class FragmentPoint : BaseFragment<PointViewModel>() {

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun bindData() {
        super.bindData()

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val examId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.ID_EXAM, 0)

        val bundle: Bundle = requireArguments()
        listExamQuestion = bundle.getSerializable("listExamQuestion") as ArrayList<ExamQuestion>
        val listAnswer = getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
        val answerList = HashMap<String,Int?>()

        for(i in 0 until listAnswer.size){
            val value = listAnswer[i]
            if(value == -1){
                answerList[(i+1).toString()] = null
            }else{
                answerList[(i+1).toString()] = listExamQuestion[i].answer_list[listAnswer[i]].answer_id
            }
        }

        val startTime = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.START_DO_TEST,"").toString()

        val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        val finishTime = sdf.format(Date()).toString()

        val requestPoint = RequestPoint(userId, examId, answerList,startTime, finishTime)

        viewModel.getResult(header,requestPoint)

        viewModel.scoreLiveData.observe(viewLifecycleOwner){
            progressPoint.apply {
                progressMax = 100f
                setProgressWithAnimation(it,3000)
            }

            txtPoint.text = it.toInt().toString()
            notifiPoint.text = "Bạn nhận được +${it.toInt()} điểm kiểm tra"
            completePercent.text = "${it.toInt()}%"
        }

        viewModel.numberCorrectLiveData.observe(viewLifecycleOwner){
            numberCorrect.text = "$it câu hỏi"
        }

        viewModel.skipNumberLiveData.observe(viewLifecycleOwner){
            skipNumber.text = it.toString()
        }

        viewModel.wrongNumberLiveData.observe(viewLifecycleOwner){
            wrongNumber.text = it.toString()
        }

        viewModel.listExamResultLiveData.observe(viewLifecycleOwner){
            val t = it
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

    private fun getListAnswer(key: String?): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun initUi() {

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
        graphView.addSeries(lineSeries)

        backPoint.setOnClickListener {
            activity?.finish()
        }

        doAgainTest.setOnClickListener {
            activity?.finish()
        }

        finishAndSaved.setOnClickListener {
            val intent = Intent(requireActivity(),HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        seeAnswer.setOnClickListener {
            val fragmentAnswer = FragmentAnswer()
            val fm: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.replace(R.id.changeIdExam,fragmentAnswer).addToBackStack(null).commit()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point, container, false)
    }
}