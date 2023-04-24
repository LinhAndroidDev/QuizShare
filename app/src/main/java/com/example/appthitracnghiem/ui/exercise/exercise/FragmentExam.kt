package com.example.appthitracnghiem.ui.exercise.exercise

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginStart
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Exam
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.fragment_list_test.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_logout.*
import kotlinx.android.synthetic.main.popup_list_question.*
import kotlinx.android.synthetic.main.popup_list_question.view.*

@Suppress("DEPRECATION", "NAME_SHADOWING")
class FragmentExam : BaseFragment<ExamViewModel>() {
    private lateinit var listQuestion: MutableList<PositiveQuestion>
    private lateinit var menuQuestionAdapter: MenuQuestionAdapter
    lateinit var countDownTimer: CountDownTimer
    var totalCount: Int = 0
    var minutes: Int = 0
    var seconds: Int = 0
    var positionQuestion: Int = 0
    private var SIZE_LIST_QUIZ: Int = 0
    lateinit var listExamQuestion: MutableList<ExamQuestion>
    var onSelectAnswer: ((Int) -> Unit)? = null

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val time = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.TIME_EXAM, 0)
        setTime(time)

        click()
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                layoutLoading.visibility = View.VISIBLE
            } else {
                layoutLoading.visibility = View.GONE
            }
        }

        viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner) {
            listExamQuestion = it
            SIZE_LIST_QUIZ = it.size
            setTextView(positionQuestion)
        }

        val user_id = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.USER_ID, 0)
        val accessToken =
            viewModel.mPreferenceUtil.defaultPref().getString(PreferenceKey.AUTHORIZATION, "")
                .toString()
        val id_exam = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.ID_EXAM, 0)
        val requestExamQuestion: RequestExamQuestion = RequestExamQuestion(user_id, id_exam)
        viewModel.getExamListQuestion(accessToken, requestExamQuestion)
    }

    private fun setTime(time: Int) {
        totalCount = time * 60
        countDownTimer = object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                totalCount--
                minutes = totalCount / 60
                seconds = totalCount % 60

                if (minutes == 0 && seconds < 1) {
                    this.cancel()
                    val fragmentPoint: FragmentPoint = FragmentPoint()

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
                if (countTime != null && txtTime != null) {
                    if (seconds < 10) {
                        txtTime.text = "Còn lại $minutes:0$seconds phút"
                    } else if (minutes < 1) {
                        txtTime.text = "Còn lại $minutes:$seconds giây"
                    } else {
                        txtTime.text = "Còn lại $minutes:$seconds phút"
                    }
                    countTime.progress = (totalCount * 100 / (time * 60)).toFloat()
                }
            }

            override fun onFinish() {
                this.start()
            }

        }.start()
    }

    private fun click() {
        nextQuestion.setOnClickListener {
            if (positionQuestion < SIZE_LIST_QUIZ - 1) {
                onSelectAnswer?.invoke(positionQuestion)
                positionQuestion++
                setTextView(positionQuestion)
            }
        }

        backQuestion.setOnClickListener {
            if (positionQuestion > 0) {
                positionQuestion--
                setTextView(positionQuestion)
            }
        }

        menuQuestion.setOnClickListener {
            showMenuQuestion(menuQuestion, R.layout.popup_list_question, 0, 160, Gravity.BOTTOM)
        }

        finishQuiz.setOnClickListener {
            layoutSubmit.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutExercise.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        50f,
                        50f,
                        Shader.TileMode.MIRROR
                    )
                )
            }
        }

        submit.setOnClickListener {
            countDownTimer.cancel()
            val fragmentPoint: FragmentPoint = FragmentPoint()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_up_enter,
                R.anim.anim_ignored_out,
                R.anim.anim_ignored_in,
                R.anim.anim_down_enter
            )
            fm.replace(R.id.changeIdExam, fragmentPoint).addToBackStack(null).commit()
        }

        backSubmit.setOnClickListener {
            layoutSubmit.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutExercise.setRenderEffect(null)
            }
        }

        backExercise.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun showMenuQuestion(view: View, popupViewId: Int, x: Int, y: Int, gravity: Int) {
        val popUpView: View = View.inflate(requireActivity(), popupViewId, null)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable: Boolean = true

        val popupWindow: PopupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAtLocation(view, gravity, x, y)

        listQuestion = mutableListOf()
        for (i in 0 until SIZE_LIST_QUIZ) {
            listQuestion.add(PositiveQuestion(i + 1))
        }

        menuQuestionAdapter = MenuQuestionAdapter(requireActivity(), listQuestion)
        menuQuestionAdapter.onClickItem = { positionItem ->
            positionQuestion = positionItem
            setTextView(positionQuestion)
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        val arrayTxtQuestion = arrayListOf<TextView>()

        titleExam.text = listExamQuestion[psQuestion].question_title
        val sizeAnswer = listExamQuestion[psQuestion].answer_list.size
        llContainerAnswerOptions.removeAllViews()
        arrayTxtQuestion.clear()
        for (i in 0 until sizeAnswer) {
            val txtQuesion = TextView(requireActivity())
            llContainerAnswerOptions.addView(txtQuesion)
            arrayTxtQuestion.add(txtQuesion)
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(16, 16, 16, 16)
            txtQuesion.setPadding(32, 24, 32, 24)
            txtQuesion.layoutParams = params
            txtQuesion.textSize = 16F
            txtQuesion.text = listExamQuestion[psQuestion].answer_list[i].content
            txtQuesion.setTextColor(Color.BLACK)
            txtQuesion.setBackgroundResource(R.drawable.un_select_text_view)

            txtQuesion.setOnClickListener {
                for(i in 0 until arrayTxtQuestion.size){
                    arrayTxtQuestion[i].setBackgroundResource(R.drawable.un_select_text_view)
                }
                txtQuesion.setBackgroundResource(R.drawable.select_text_view)
            }
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
        return inflater.inflate(R.layout.fragment_exam, container, false)
    }
}