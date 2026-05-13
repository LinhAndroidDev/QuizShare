package com.example.appthitracnghiem.ui.exercise.exercise.exam

import android.annotation.SuppressLint
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.databinding.FragmentExamBinding
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.point.FragmentPoint
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.ui.exercise.exercise.ExamSessionViewModel
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION", "NAME_SHADOWING")
@AndroidEntryPoint
class FragmentExam : BaseFragment<ExamViewModel>() {
    private var _binding: FragmentExamBinding? = null
    private val binding get() = _binding!!

    private val examSessionViewModel: ExamSessionViewModel by activityViewModels()

    private lateinit var menuQuestionAdapter: MenuQuestionAdapter

    private val examAnswerAdapter = ExamAnswerOptionAdapter()

    private var countDownTimer: CountDownTimer? = null

    private var timeTotal: Int = 0

    private var minutes: Int = 0

    private var seconds: Int = 0

    private var positiveQuestion: Int = 0

    private var sizeListQuestion: Int = 0

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    private var listQuestion: MutableList<PositiveQuestion> = mutableListOf()

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            examSessionViewModel.initWithQuestions(it)
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
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
                        putString(
                            ExamSessionExtras.ARG_EXAM_START_TIMESTAMP,
                            requireArguments().getString(ExamSessionExtras.ARG_EXAM_START_TIMESTAMP).orEmpty(),
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
        }

        binding.backQuestion.setOnClickListener {
            if (positiveQuestion > 0) {
                positiveQuestion--
                setTextView(positiveQuestion)
            }
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
        }

        binding.menuQuestion.setOnClickListener {
            binding.menuQuestion.showMenuQuestion()
        }

        binding.finishQuiz.setOnClickListener {
            showLayoutSubmit()
        }

        binding.recyclerExamAnswerOptions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerExamAnswerOptions.adapter = examAnswerAdapter
        binding.recyclerExamAnswerOptions.setHasFixedSize(false)
        binding.recyclerExamAnswerOptions.isNestedScrollingEnabled = false
        binding.recyclerExamAnswerOptions.itemAnimator = null
        examAnswerAdapter.onOptionClick = { optionIndex ->
            if (::listExamQuestion.isInitialized) {
                examSessionViewModel.setSelection(positiveQuestion, optionIndex)
                examSessionViewModel.questionAt(positiveQuestion)?.let { examAnswerAdapter.submit(it) }
            }
        }

        binding.submit.setOnClickListener {
            countDownTimer?.cancel()
            val fragmentPoint = FragmentPoint()
            val bundle = Bundle().apply {
                putSerializable("listExamQuestion", listExamQuestion)
                putInt(
                    ExamSessionExtras.ARG_EXAM_ID,
                    requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0),
                )
                putString(
                    ExamSessionExtras.ARG_EXAM_START_TIMESTAMP,
                    requireArguments().getString(ExamSessionExtras.ARG_EXAM_START_TIMESTAMP).orEmpty(),
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
                examSessionViewModel.markAllUnanswered(sizeListQuestion)
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
    private fun View.showMenuQuestion() {
        val popUpView: View = View.inflate(requireActivity(), R.layout.popup_list_question, null)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAtLocation(this, Gravity.BOTTOM, 0, 290)

        listQuestion = mutableListOf()
        for (i in 0 until sizeListQuestion) {
            listQuestion.add(PositiveQuestion(i + 1,null))
        }

        menuQuestionAdapter = MenuQuestionAdapter(
            requireActivity(),
            listQuestion,
            isExamQuestionAnswered = { examSessionViewModel.isQuestionAnswered(it) },
        )
        menuQuestionAdapter.onClickItem = { positionItem ->
            positiveQuestion = positionItem
            binding.txtPositionQuiz.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
            setTextView(positiveQuestion)
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        examSessionViewModel.ensureVisited(psQuestion)
        val taking = examSessionViewModel.questionAt(psQuestion) ?: return
        binding.titleExam.text = taking.source.question_title
        examAnswerAdapter.submit(taking)
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