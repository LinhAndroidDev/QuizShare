package com.example.appthitracnghiem.ui.exercise.exercise.answer

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_answer.*
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.layout_loading.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

@Suppress("DEPRECATION", "CAST_NEVER_SUCCEEDS")
class FragmentAnswer : BaseFragment<AnswerViewModel>() {
    private lateinit var listQuestion: MutableList<PositiveQuestion>

    private lateinit var menuQuestionAdapter: MenuQuestionAdapter

    private var POSITIVE_QUESTION: Int = 0

    private var SIZE_LIST_QUESTION: Int = 0

    private lateinit var listExamQuestion: ArrayList<ExamQuestion>

    private lateinit var listAnswer: ArrayList<Int>

    var onClickNextQuestion: ((Int) -> Unit)? = null

    companion object {
        var arrayTxtQuestion = arrayListOf<TextView>()
        var listResult: ArrayList<Int> = arrayListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        answer1.setBackgroundResource(R.drawable.bg_answer_fail)
//        answer3.setBackgroundResource(R.drawable.select_text_view)

        listAnswer = arrayListOf()

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
                listAnswer.add(-1)
            }
            txtPositionQuizAnswer.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
            setTextView(POSITIVE_QUESTION)
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val accessToken =
            viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION, "")
                .toString()
        val idHistoryExam = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.EXAM_ID_HISTORY,0)
        val idExam = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.ID_EXAM, 0)
        val requestExamQuestion = RequestExamQuestion(userId, idExam)
        viewModel.getExamListQuestion(accessToken, requestExamQuestion)

        val requestAnswer = RequestAnswer(userId, idHistoryExam)
        viewModel.getExamResult(accessToken, requestAnswer)

        viewModel.listAnswerLiveData.observe(viewLifecycleOwner) {
            val json = JSONObject(it)
            val hashMap: HashMap<String,Int> = toValue(json) as HashMap<String, Int>
        }
    }

     private fun JSONObject.toMap(): Map<String, Any?> =
        keys().asSequence().associateWith { key -> toValue(get(key)) }

    private fun JSONArray.toList(): List<Any?> =
        (0 until length()).map { index -> toValue(get(index)) }

    private fun toValue(element: Any) = when (element) {
        JSONObject.NULL -> null
        is JSONObject -> element.toMap()
        is JSONArray -> element.toList()
        else -> element
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

    @SuppressLint("SetTextI18n")
    private fun initUi() {

        setStatusBar()

        nextQuestionAnswer.setOnClickListener {
            if (POSITIVE_QUESTION < SIZE_LIST_QUESTION - 1) {
                POSITIVE_QUESTION++
                setTextView(POSITIVE_QUESTION)
                onClickNextQuestion?.invoke(POSITIVE_QUESTION)
            }
            txtPositionQuizAnswer.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
        }

        backQuestionAnswer.setOnClickListener {
            if (POSITIVE_QUESTION > 0) {
                POSITIVE_QUESTION--
                setTextView(POSITIVE_QUESTION)
            }
            txtPositionQuizAnswer.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
        }

        menuQuestionAnswer.setOnClickListener {
            showMenuQuestion(menuQuestionAnswer, R.layout.popup_list_question, 0, 250, Gravity.BOTTOM)
        }

        backAnswer.setOnClickListener {
            activity?.onBackPressed()
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
            setTextView(POSITIVE_QUESTION)
            txtPositionQuizAnswer.text = "Câu " + (POSITIVE_QUESTION+1) + " trên " + SIZE_LIST_QUESTION
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)
        recycleQuestion.isEnabled = false

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        titleAnswer.text = listExamQuestion[psQuestion].question_title
        val sizeAnswer = listExamQuestion[psQuestion].answer_list.size
        llContainerOptions.removeAllViews()
        arrayTxtQuestion.clear()

        for (i in 0 until sizeAnswer) {
            val txtQuestion = TextView(requireActivity())
            txtQuestion.isEnabled = false

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
        }
        listResult = getListAnswer(PreferenceKey.ARRAY_LIST_RESULTS)
        val t = listResult
        if(listResult[psQuestion] != arrAnswer[psQuestion]){
            arrayTxtQuestion[listResult[psQuestion]].setBackgroundResource(R.drawable.bg_answer_fail)
        }
    }

    /** Create Text Answer **/
    private fun createTextAnswer(
        arrayTxt: ArrayList<TextView>,
        txt: TextView,
        position: Int,
        i: Int,
    ) {
        llContainerOptions.addView(txt)
        arrayTxt.add(txt)
        val params =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(16, 16, 16, 16)
        txt.setPadding(32, 24, 32, 24)
        txt.layoutParams = params
        txt.textSize = 16F
        txt.text = listExamQuestion[position].answer_list[i].content
        txt.setTextColor(Color.BLACK)
        txt.setBackgroundResource(R.drawable.un_select_text_view)
    }

    private fun getListAnswer(key: String?): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }
}