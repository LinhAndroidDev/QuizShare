@file:Suppress("DEPRECATION")

package com.example.appthitracnghiem.ui.exercise.exercise.answer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentAnswerBinding
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.model.examui.ExamReviewQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.data.remote.dto.request.RequestAnswer
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION", "CAST_NEVER_SUCCEEDS")
@AndroidEntryPoint
class FragmentAnswer : BaseFragment<AnswerViewModel>() {
    private var _binding: FragmentAnswerBinding? = null
    private val binding get() = _binding!!

    private lateinit var listQuestion: MutableList<PositiveQuestion>


    private lateinit var menuQuestionAdapter: MenuQuestionAdapter

    private var positiveQuestion: Int = 0

    private var sizeListQuestion: Int = 0

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    /** Mô hình màn hình xem lại (mỗi câu + đáp án đã gán drawable + chip menu). */
    private var reviewSession: List<ExamReviewQuestion> = emptyList()

    /** Parsed API `exam_result`: question_id → chosen answer_id (null = không chọn). */
    private var examResultByQuestionId: Map<Int, Int?>? = null

    private val answerOptionsAdapter = AnswerReviewOptionAdapter()

    var onClickNextQuestion: ((Int) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fm = requireActivity().supportFragmentManager
                    if (fm.isStateSaved) return
                    if (fm.backStackEntryCount > 0) {
                        fm.popBackStack()
                    } else {
                        requireActivity().finish()
                    }
                }
            },
        )

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

        viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner) { examQuestions ->
            examQuestions?.let {
                listExamQuestion = it
                sizeListQuestion = it.size
                positiveQuestion = 0
                rebuildReviewSession()
                binding.txtPositionQuizAnswer.text =
                    getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
                setTextView(positiveQuestion)
            }
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val idHistoryExam = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.EXAM_ID_HISTORY, 0)
        val idExam = requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0)
        viewModel.getExamListQuestion(RequestExamQuestion(userId, idExam))
        viewModel.getExamResult(RequestAnswer(userId, idHistoryExam))

        viewModel.listAnswerLiveData.observe(viewLifecycleOwner) { raw ->
            examResultByQuestionId = parseExamResultByQuestionId(raw)
            rebuildReviewSession()
            if (::listExamQuestion.isInitialized && listExamQuestion.isNotEmpty()) {
                setTextView(positiveQuestion)
            }
        }
    }

    private fun parseExamResultByQuestionId(raw: Map<String, Int?>?): Map<Int, Int?> =
        raw.orEmpty().mapNotNull { (key, value) ->
            key.toIntOrNull()?.let { questionId -> questionId to value }
        }.toMap()

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

    @SuppressLint("SetTextI18n")
    private fun initUi() {

        val titleText: String = requireArguments().getString("title").toString()
        binding.txtTiltleAnswer.text = titleText

        setStatusBar()

        binding.nextQuestionAnswer.setOnClickListener {
            if (positiveQuestion < sizeListQuestion - 1) {
                positiveQuestion++
                setTextView(positiveQuestion)
                onClickNextQuestion?.invoke(positiveQuestion)
            }
            binding.txtPositionQuizAnswer.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
        }

        binding.backQuestionAnswer.setOnClickListener {
            if (positiveQuestion > 0) {
                positiveQuestion--
                setTextView(positiveQuestion)
            }
            binding.txtPositionQuizAnswer.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
        }

        binding.menuQuestionAnswer.setOnClickListener {
            showMenuQuestion(binding.menuQuestionAnswer, R.layout.popup_list_question, 0, 290, Gravity.BOTTOM)
        }

        binding.backAnswer.setOnClickListener {
            val fm = requireActivity().supportFragmentManager
            if (!fm.isStateSaved && fm.backStackEntryCount > 0) {
                fm.popBackStack()
            } else {
                requireActivity().finish()
            }
        }

        binding.recyclerAnswerOptions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAnswerOptions.adapter = answerOptionsAdapter
        binding.recyclerAnswerOptions.setHasFixedSize(false)
        binding.recyclerAnswerOptions.isNestedScrollingEnabled = false
        binding.recyclerAnswerOptions.itemAnimator = null
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

        menuQuestionAdapter = MenuQuestionAdapter(
            requireActivity(),
            listQuestion,
            reviewChipStates = if (reviewSession.size == sizeListQuestion && reviewSession.isNotEmpty()) {
                reviewSession.map { it.chipState }
            } else {
                null
            },
            currentQuestionIndex = positiveQuestion,
            isExamQuestionAnswered = { pos ->
                reviewSession.getOrNull(pos)?.isUserAnswered == true
            },
        )
        menuQuestionAdapter.onClickItem = { positionItem ->
            positiveQuestion = positionItem
            setTextView(positiveQuestion)
            binding.txtPositionQuizAnswer.text =
                getString(R.string.format_exam_question_position, positiveQuestion + 1, sizeListQuestion)
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)
        recycleQuestion.isEnabled = false

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    private fun rebuildReviewSession() {
        if (!::listExamQuestion.isInitialized || listExamQuestion.isEmpty()) {
            reviewSession = emptyList()
            return
        }
        reviewSession = listExamQuestion.map { q ->
            ExamReviewQuestion.fromApi(q, examResultByQuestionId)
        }
    }

    fun setTextView(psQuestion: Int) {
        if (!::listExamQuestion.isInitialized || listExamQuestion.isEmpty()) return
        if (psQuestion !in listExamQuestion.indices) return
        if (reviewSession.size != listExamQuestion.size) {
            rebuildReviewSession()
        }

        val row = reviewSession.getOrNull(psQuestion) ?: return
        binding.titleAnswer.text = row.source.question_title
        answerOptionsAdapter.submit(row)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}