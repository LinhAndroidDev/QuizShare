@file:Suppress("DEPRECATION")

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
import com.example.appthitracnghiem.databinding.FragmentAnswerBinding
import com.example.appthitracnghiem.model.ExamQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter
import com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
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

    var listAnswer: ArrayList<Int> = arrayListOf()

    var onClickNextQuestion: ((Int) -> Unit)? = null

    companion object {
        var arrayTxtQuestion = arrayListOf<TextView>()
        var listResult: ArrayList<Int> = arrayListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        answer1.setBackgroundResource(R.drawable.bg_answer_fail)
//        answer3.setBackgroundResource(R.drawable.select_text_view)

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
                for (i in 0 until sizeListQuestion) {
                    listAnswer.add(-1)
                }
                binding.txtPositionQuizAnswer.text = "Câu " + (positiveQuestion+1) + " trên " + sizeListQuestion
                setTextView(positiveQuestion)
            }
        }

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val idHistoryExam = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.EXAM_ID_HISTORY, 0)
        val idExam = viewModel.mPreferenceUtil.defaultPref().getInt(PreferenceKey.ID_EXAM, 0)
        viewModel.getExamListQuestion(RequestExamQuestion(userId, idExam))
        viewModel.getExamResult(RequestAnswer(userId, idHistoryExam))

        viewModel.listAnswerLiveData.observe(viewLifecycleOwner) { examResult ->
            if (!examResult.isNullOrBlank()) {
                runCatching { JSONObject(examResult).toMap() }
            }
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

        val titleText: String = requireArguments().getString("title").toString()
        binding.txtTiltleAnswer.text = titleText

        setStatusBar()

        binding.nextQuestionAnswer.setOnClickListener {
            if (positiveQuestion < sizeListQuestion - 1) {
                positiveQuestion++
                setTextView(positiveQuestion)
                onClickNextQuestion?.invoke(positiveQuestion)
            }
            binding.txtPositionQuizAnswer.text = "Câu " + (positiveQuestion+1) + " trên " + sizeListQuestion
        }

        binding.backQuestionAnswer.setOnClickListener {
            if (positiveQuestion > 0) {
                positiveQuestion--
                setTextView(positiveQuestion)
            }
            binding.txtPositionQuizAnswer.text = "Câu " + (positiveQuestion+1) + " trên " + sizeListQuestion
        }

        binding.menuQuestionAnswer.setOnClickListener {
            showMenuQuestion(binding.menuQuestionAnswer, R.layout.popup_list_question, 0, 250, Gravity.BOTTOM)
        }

        binding.backAnswer.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
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
            setTextView(positiveQuestion)
            binding.txtPositionQuizAnswer.text = "Câu " + (positiveQuestion+1) + " trên " + sizeListQuestion
        }
        val recycleQuestion: RecyclerView = popUpView.findViewById(R.id.recycleViewMenuQuestion)
        recycleQuestion.isEnabled = false

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 5)

        recycleQuestion.layoutManager = mLayoutManager
        recycleQuestion.adapter = menuQuestionAdapter
    }

    @SuppressLint("ResourceAsColor")
    fun setTextView(psQuestion: Int) {
        binding.titleAnswer.text = listExamQuestion[psQuestion].question_title
        val sizeAnswer = listExamQuestion[psQuestion].answer_list.size
        binding.llContainerOptions.removeAllViews()
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
                listAnswer[positiveQuestion] = i
            }
        }

        val arrAnswer: ArrayList<Int> = getListAnswer(PreferenceKey.ARRAY_LIST_ANSWER)
        if (arrAnswer[psQuestion] >= 0) {
            arrayTxtQuestion[arrAnswer[psQuestion]].setBackgroundResource(R.drawable.select_text_view)
        }
        listResult = getListAnswer(PreferenceKey.ARRAY_LIST_RESULTS)
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
        binding.llContainerOptions.addView(txt)
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
        txt.setLineSpacing(2f,1.4f)
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
    ): View {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}