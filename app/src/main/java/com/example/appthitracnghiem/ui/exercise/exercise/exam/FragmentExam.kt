@file:Suppress("DEPRECATION")

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
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.point.FragmentPoint
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.fragment_list_test.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_logout.*
import kotlinx.android.synthetic.main.layout_menu_question.*
import kotlinx.android.synthetic.main.popup_list_question.*
import kotlinx.android.synthetic.main.popup_list_question.view.*
import java.lang.reflect.Type

@Suppress("DEPRECATION", "NAME_SHADOWING")
class FragmentExam : BaseFragment<ExamViewModel>() {

    private lateinit var menuQuestionAdapter: MenuQuestionAdapter

    private lateinit var countDownTimer: CountDownTimer

    private var TIME_TOTAL: Int = 0

    private var MINUTES: Int = 0

    private var SECONDS: Int = 0

    private var POSITIVE_QUESTION: Int = 0

    private var SIZE_LIST_QUESTION: Int = 0

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    private lateinit var listAnswer: ArrayList<Int>

    var onClickChangeQuestion: ((Boolean) -> Unit)? = null

    companion object {
        var arrayTxtQuestion = arrayListOf<TextView>()
        var listQuestion: MutableList<PositiveQuestion> = mutableListOf()
        var listResult: ArrayList<Int> = arrayListOf()
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAnswer = arrayListOf()

        val time = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.TIME_EXAM, 0)
        setTime(time)

        initUi()
    }

    @SuppressLint("SetTextI18n")
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
            SIZE_LIST_QUESTION = it.size
            for (i in 0 until SIZE_LIST_QUESTION) {
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
            txtPositionQuiz.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
            setTextView(POSITIVE_QUESTION)
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val accessToken =
            viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION, "")
                .toString()
        val idExam = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.ID_EXAM, 0)
        val requestExamQuestion = RequestExamQuestion(userId, idExam)
        viewModel.getExamListQuestion(accessToken, requestExamQuestion)
    }

    private fun setTime(time: Int) {
        TIME_TOTAL = time * 60
        countDownTimer = object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                TIME_TOTAL--
                MINUTES = TIME_TOTAL / 60
                SECONDS = TIME_TOTAL % 60

                if (MINUTES == 0 && SECONDS < 1) {
                    this.cancel()
                    val fragmentPoint = FragmentPoint()
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
                    if (SECONDS < 10) {
                        txtTime.text = "Còn lại $MINUTES:0$SECONDS phút"
                    } else if (MINUTES < 1) {
                        txtTime.text = "Còn lại $MINUTES:$SECONDS giây"
                    } else {
                        txtTime.text = "Còn lại $MINUTES:$SECONDS phút"
                    }
                    countTime.progress = (TIME_TOTAL * 100 / (time * 60)).toFloat()
                }
            }

            override fun onFinish() {
                this.start()
            }

        }.start()
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

        nextQuestion.setOnClickListener {
            if (POSITIVE_QUESTION < SIZE_LIST_QUESTION - 1) {
                POSITIVE_QUESTION++
                setTextView(POSITIVE_QUESTION)
            } else {
                showLayoutSubmit()
            }
            txtPositionQuiz.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }

        backQuestion.setOnClickListener {
            if (POSITIVE_QUESTION > 0) {
                POSITIVE_QUESTION--
                setTextView(POSITIVE_QUESTION)
            }
            txtPositionQuiz.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }

        menuQuestion.setOnClickListener {
            showMenuQuestion(menuQuestion, R.layout.popup_list_question, 0, 250, Gravity.BOTTOM)
        }

        finishQuiz.setOnClickListener {
            showLayoutSubmit()
        }

        submit.setOnClickListener {
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
            getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
            countDownTimer.cancel()
            val bundle = Bundle()
            bundle.putSerializable("listExamQuestion",listExamQuestion)
            val fragmentPoint = FragmentPoint()
            val fm: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_up_enter,
                R.anim.anim_ignored_out,
                R.anim.anim_ignored_in,
                R.anim.anim_down_enter
            )
            fm.replace(R.id.changeIdExam, fragmentPoint).addToBackStack(null).commit()
            fragmentPoint.arguments = bundle
        }

        backSubmit.setOnClickListener {
            layoutSubmit.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutExercise.setRenderEffect(null)
            }
        }

        backExercise.setOnClickListener {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            alertDialog.setTitle("Thông báo")
            alertDialog.setIcon(R.drawable.icon_app_thitn)
            alertDialog.setMessage("Nếu bạn thoát điểm bài thi sẽ không được tính?")
            alertDialog.setPositiveButton("Vẫn thoát") { _, _ ->
                for (i in 0 until SIZE_LIST_QUESTION) {
                    listAnswer.add(-1)
                }
                saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
                activity?.onBackPressed()
            }
            alertDialog.setNegativeButton("Không") { _, _ -> }
            alertDialog.show()
        }
    }

    private fun showLayoutSubmit() {
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

    @SuppressLint("SetTextI18n")
    private fun showMenuQuestion(view: View, popupViewId: Int, x: Int, y: Int, gravity: Int) {
        val popUpView: View = View.inflate(requireActivity(), popupViewId, null)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAtLocation(view, gravity, x, y)

        listQuestion = mutableListOf()
        for (i in 0 until SIZE_LIST_QUESTION) {
            listQuestion.add(PositiveQuestion(i + 1,null))
        }

        menuQuestionAdapter = MenuQuestionAdapter(requireActivity(), listQuestion)
        menuQuestionAdapter.onClickItem = { positionItem ->
            POSITIVE_QUESTION = positionItem
            txtPositionQuiz.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
            setTextView(POSITIVE_QUESTION)
            saveListAnswer(listAnswer, PreferenceKey.ARRAY_LIST_ANSWER)
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        titleExam.text = listExamQuestion[psQuestion].question_title
        val sizeAnswer = listExamQuestion[psQuestion].answer_list.size
        llContainerAnswerOptions.removeAllViews()
        arrayTxtQuestion.clear()

        for (i in 0 until sizeAnswer) {
            val txtQuestion = TextView(requireActivity())

            createTextAnswer(arrayTxtQuestion, txtQuestion, psQuestion, i)

            txtQuestion.setOnClickListener {
                for (j in 0 until arrayTxtQuestion.size) {
                    arrayTxtQuestion[j].setBackgroundResource(R.drawable.un_select_text_view)
                }
                txtQuestion.setBackgroundResource(R.drawable.select_text_view)
                listAnswer[POSITIVE_QUESTION] = i
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
        llContainerAnswerOptions.addView(txt)
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
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam, container, false)
    }
}