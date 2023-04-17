package com.example.appthitracnghiem.ui.exercise.exercise

import android.annotation.SuppressLint
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.layout_logout.*
import kotlinx.android.synthetic.main.popup_list_question.*
import kotlinx.android.synthetic.main.popup_list_question.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentExam.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentExam : BaseFragment<ExamViewModel>() {
    private lateinit var listQuestion: MutableList<PositiveQuestion>
    private lateinit var menuQuestionAdapter: MenuQuestionAdapter
    lateinit var countDownTimer: CountDownTimer
    var totalCount: Int = 0
    var minutes: Int = 0
    var seconds: Int = 0
    var position: Int = 0
    var size: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val time = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.TIME_EXAM,0)
        setTime(1)

        click()
    }

    override fun bindData() {
        super.bindData()

        viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner){
            titleExam.text = it[position].question_title
            choose1.text = it[position].answer_list[0].content
            choose2.text = it[position].answer_list[1].content
            size = it[position].answer_list.size
            if(size > 2){
                choose3.text = it[position].answer_list[2].content
            }else{
                choose3.visibility = View.INVISIBLE
            }
            if(size > 3){
                choose4.text = it[position].answer_list[3].content
            }else{
                choose4.visibility = View.INVISIBLE
            }
        }

        val user_id = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.USER_ID,0)
        val accessToken = viewModel.mPreferenceUtil.defaultPref().getString(PreferenceKey.AUTHORIZATION,"").toString()
        val id_exam = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.ID_EXAM,0)
        val requestExamQuestion: RequestExamQuestion = RequestExamQuestion(user_id,id_exam)
        viewModel.getExamListQuestion(accessToken, requestExamQuestion)
    }

    private fun setTime(time: Int) {
        totalCount = time * 60 + 5
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
                    } else if(minutes < 1){
                        txtTime.text = "Còn lại $minutes:$seconds giây"
                    } else{
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
            if(position < size){
            position++
            setUnText()
            viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner){
                titleExam.text = it[position].question_title
                choose1.text = it[position].answer_list[0].content
                choose2.text = it[position].answer_list[1].content
                choose3.text = it[position].answer_list[2].content
                val size = it[position].answer_list.size
                if(size > 3){
                    choose4.text = it[position].answer_list[3].content
                }else{
                    choose4.visibility = View.INVISIBLE
                }
            }
        }
        }

        backQuestion.setOnClickListener {
            if(position > 0){
                position--
                setUnText()
                viewModel.listExamQuestionLiveData.observe(viewLifecycleOwner){
                    titleExam.text = it[position].question_title
                    choose1.text = it[position].answer_list[0].content
                    choose2.text = it[position].answer_list[1].content
                    choose3.text = it[position].answer_list[2].content
                    val size = it[position].answer_list.size
                    if(size > 3){
                        choose4.text = it[position].answer_list[3].content
                        choose4.visibility = View.VISIBLE
                    }else{
                        choose4.visibility = View.INVISIBLE
                    }
                }
            }
        }

        choose1.setOnClickListener {
            clickChoose(choose1)
        }

        choose2.setOnClickListener {
            clickChoose(choose2)
        }

        choose3.setOnClickListener {
            clickChoose(choose3)
        }

        choose4.setOnClickListener {
            clickChoose(choose4)
        }

        menuQuestion.setOnClickListener {
            showMenuQuestion(menuQuestion, R.layout.popup_list_question, 0, -560, Gravity.TOP)
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

    private fun clickChoose(choose: TextView) {
        choose1.isSelected = false
        choose2.isSelected = false
        choose3.isSelected = false
        choose4.isSelected = false
        setUnText()

        choose.isSelected = true
        choose.setBackgroundResource(R.drawable.select_text_view)
    }

    private fun setUnText() {
        choose1.setBackgroundResource(R.drawable.un_select_text_view)
        choose2.setBackgroundResource(R.drawable.un_select_text_view)
        choose3.setBackgroundResource(R.drawable.un_select_text_view)
        choose4.setBackgroundResource(R.drawable.un_select_text_view)
    }

    private fun showMenuQuestion(view: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable: Boolean = true

        val popupWindow: PopupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(view, x, y, position)

        listQuestion = mutableListOf()
        listQuestion.add(PositiveQuestion(1))
        listQuestion.add(PositiveQuestion(2))
        listQuestion.add(PositiveQuestion(3))
        listQuestion.add(PositiveQuestion(4))
        listQuestion.add(PositiveQuestion(5))
        listQuestion.add(PositiveQuestion(6))
        listQuestion.add(PositiveQuestion(7))
        listQuestion.add(PositiveQuestion(8))
        listQuestion.add(PositiveQuestion(9))
        listQuestion.add(PositiveQuestion(10))
        listQuestion.add(PositiveQuestion(11))
        listQuestion.add(PositiveQuestion(12))

        menuQuestionAdapter = MenuQuestionAdapter(requireActivity(), listQuestion)

//        val grid: GridLayoutManager = GridLayoutManager(requireActivity(), 5)

        val v = LayoutInflater.from(requireActivity()).inflate(R.layout.popup_list_question, null)

        val recycleQuestion: RecyclerView = v.findViewById(R.id.recycleViewMenuQuestion)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(),5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
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