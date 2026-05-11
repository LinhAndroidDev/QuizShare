package com.example.appthitracnghiem.ui.exercise.exercise.exam

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.view.*
import android.widget.LinearLayout.LayoutParams
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.databinding.FragmentExamBinding
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.point.FragmentPoint
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.content.edit

@Suppress("DEPRECATION", "NAME_SHADOWING")
@AndroidEntryPoint
class FragmentExam : BaseFragment<ExamViewModel>() {
    private var _binding: FragmentExamBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuQuestionAdapter: MenuQuestionAdapter

    private var countDownTimer: CountDownTimer? = null

    private var timeTotal: Int = 0

    private var minutes: Int = 0

    private var seconds: Int = 0

    private var positiveQuestion: Int = 0

    private var sizeListQuestion: Int = 0

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    private lateinit var listAnswer: ArrayList<Int>

    private var arrayTxtQuestion = arrayListOf<TextView>()
    private var listQuestion: MutableList<PositiveQuestion> = mutableListOf()
    private var listResult: ArrayList<Int> = arrayListOf()

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listAnswer = arrayListOf()
        super.onViewCreated(view, savedInstanceState)

        val timeMinutes = requireArguments().getInt(ExamSessionExtras.ARG_TIME_MINUTES, 0)
        setTime(timeMinutes)

        initUi()
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        super.bindData()

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.root.findViewById<View>(R.id.layoutLoading).visibility = View.VISIBLE
            } else {
                binding.root.findViewById<View>(R.id.layoutLoading).visibility = View.GONE
            }
        }

        viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner) {
            listExamQuestion = it
            sizeListQuestion = it.size
            for (i in 0 until sizeListQuestion) {
                if(i == 0){
                    listAnswer.add(-1)
                }else{
                    listAnswer.add(-2)
                }
                for(j in 0 until listExamQuestion[i].answer_list.size){
                    if(listExamQuestion[i].answer_list[j].type == 1){
                        listResult.add(j)
                    }
                }
            }
            saveListAnswer(listResult, PreferenceKey.ARRAY_LIST_RESULTS)
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
            setTextView(positiveQuestion)
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val idExam = requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0)
        viewModel.getExamListQuestion(RequestExamQuestion(userId, idExam))
    }

    private fun setTime(time: Int) {
        timeTotal = time * 60
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(600000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val b = _binding ?: return
                timeTotal--
                minutes = timeTotal / 60
                seconds = timeTotal % 60

                if (minutes == 0 && seconds < 1) {
                    this.cancel()
                    if (!::listExamQuestion.isInitialized) return@onTick
                    val fragmentPoint = FragmentPoint()
                    val pointBundle = Bundle().apply {
                        putSerializable("listExamQuestion", listExamQuestion)
                        putInt(
                            ExamSessionExtras.ARG_EXAM_ID,
                            requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0),
                        )
                    }
                    fragmentPoint.arguments = pointBundle
                    val fm: FragmentTransaction? =
                        activity?.supportFragmentManager?.beginTransaction()
                    fm?.setCustomAnimations(
                        R.anim.anim_up_enter,
                        R.anim.anim_ignored_out,
                        R.anim.anim_ignored_in,
                        R.anim.anim_down_enter
                    )
                    fm?.replace(R.id.changeIdExam, fragmentPoint)?.addToBackStack(null)?.commit()
                }
                if (seconds < 10) {
                    b.txtTime.text = getString(
                        R.string.format_time_remaining_pad_minute,
                        minutes,
                        seconds,
                    )
                } else if (minutes < 1) {
                    b.txtTime.text = getString(
                        R.string.format_time_remaining_seconds,
                        minutes,
                        seconds,
                    )
                } else {
                    b.txtTime.text = getString(
                        R.string.format_time_remaining_minutes,
                        minutes,
                        seconds,
                    )
                }
                b.countTime.progress = (timeTotal * 100 / (time * 60)).toFloat()
            }

            override fun onFinish() {
                this.start()
            }

        }.also { it.start() }
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
        }
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun initUi() {

        setStatusBar()

        binding.nextQuestion.setOnClickListener {
            if (positiveQuestion < sizeListQuestion - 1) {
                positiveQuestion++
                setTextView(positiveQuestion)
            } else {
                showLayoutSubmit()
            }
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }

        binding.backQuestion.setOnClickListener {
            if (positiveQuestion > 0) {
                positiveQuestion--
                setTextView(positiveQuestion)
            }
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }

        binding.menuQuestion.setOnClickListener {
            showMenuQuestion(binding.menuQuestion, R.layout.popup_list_question, 0, 250, Gravity.BOTTOM)
        }

        binding.finishQuiz.setOnClickListener {
            showLayoutSubmit()
        }

        binding.submit.setOnClickListener {
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
            getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
            countDownTimer?.cancel()
            val fragmentPoint = FragmentPoint()
            val bundle = Bundle().apply {
                putSerializable("listExamQuestion", listExamQuestion)
                putInt(
                    ExamSessionExtras.ARG_EXAM_ID,
                    requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0),
                )
            }
            fragmentPoint.arguments = bundle
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_up_enter,
                R.anim.anim_ignored_out,
                R.anim.anim_ignored_in,
                R.anim.anim_down_enter
            )
            fm.replace(R.id.changeIdExam, fragmentPoint).addToBackStack(null).commit()
        }

        binding.backSubmit.setOnClickListener {
            binding.layoutSubmit.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.layoutExercise.setRenderEffect(null)
            }
        }

        binding.backExercise.setOnClickListener {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            alertDialog.setTitle(getString(R.string.dialog_title_notice))
            alertDialog.setIcon(R.drawable.icon_app_thitn)
            alertDialog.setMessage(getString(R.string.dialog_exit_exam_message))
            alertDialog.setPositiveButton(getString(R.string.btn_still_exit)) { _, _ ->
                for (i in 0 until sizeListQuestion) {
                    listAnswer.add(-1)
                }
                saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            alertDialog.setNegativeButton(getString(R.string.btn_no)) { _, _ -> }
            alertDialog.show()
        }
    }

    private fun showLayoutSubmit() {
        binding.layoutSubmit.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.layoutExercise.setRenderEffect(
                RenderEffect.createBlurEffect(
                    50f,
                    50f,
                    Shader.TileMode.MIRROR
                )
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMenuQuestion(view: View, popupViewId: Int, x: Int, y: Int, gravity: Int) {
        val popUpView: View = View.inflate(requireActivity(), popupViewId, null)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAtLocation(view, gravity, x, y)

        listQuestion = mutableListOf()
        for (i in 0 until sizeListQuestion) {
            listQuestion.add(PositiveQuestion(i + 1,null))
        }

        menuQuestionAdapter = MenuQuestionAdapter(requireActivity(), listQuestion)
        menuQuestionAdapter.onClickItem = { positionItem ->
            positiveQuestion = positionItem
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
            setTextView(positiveQuestion)
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        binding.titleExam.text = listExamQuestion[psQuestion].question_title
        val sizeAnswer = listExamQuestion[psQuestion].answer_list.size
        binding.llContainerAnswerOptions.removeAllViews()
        arrayTxtQuestion.clear()

        for (i in 0 until sizeAnswer) {
            val txtQuestion = TextView(requireActivity())

            createTextAnswer(arrayTxtQuestion, txtQuestion, psQuestion, i)

            txtQuestion.setOnClickListener {
                for (j in 0 until arrayTxtQuestion.size) {
                    arrayTxtQuestion[j].setBackgroundResource(R.drawable.un_select_text_view)
                }
                txtQuestion.setBackgroundResource(R.drawable.select_text_view)
                listAnswer[positiveQuestion] = i
            }
        }

        val arrAnswer: ArrayList<Int> = getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
        if (arrAnswer[psQuestion] >= 0) {
            arrayTxtQuestion[arrAnswer[psQuestion]].setBackgroundResource(R.drawable.select_text_view)
        }else if(listAnswer[psQuestion] == -2){
            listAnswer[psQuestion] = -1
        }
    }

    /** Create Text Answer **/
    private fun createTextAnswer(
        arrayTxt: ArrayList<TextView>,
        txt: TextView,
        position: Int,
        i: Int,
    ) {
        binding.llContainerAnswerOptions.addView(txt)
        arrayTxt.add(txt)
        val params =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(16, 16, 16, 16)
        txt.setPadding(32, 24, 32, 24)
        txt.layoutParams = params
        txt.textSize = 16F
        txt.text = listExamQuestion[position].answer_list[i].content
        txt.setLineSpacing(2f,1.4f)
        txt.setTextColor(Color.BLACK)
        txt.setBackgroundResource(R.drawable.un_select_text_view)
    }

    private fun saveListAnswer(list: ArrayList<Int>, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        prefs.edit {
            val gson = Gson()
            val json: String = gson.toJson(list)
            putString(key, json)
        }
    }

    private fun getListAnswer(key: String?): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        countDownTimer?.cancel()
        countDownTimer = null
        _binding = null
        super.onDestroyView()
    }
}