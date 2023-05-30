package com.example.appthitracnghiem.ui.home.createtest.question

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.CreateAnswer
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.ui.home.createtest.review.FragmentReviewCreateExam
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_exam.*
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_review_create_exam.*
import java.lang.reflect.Type

@Suppress("DEPRECATION")
class FragmentCreateExam : BaseFragment<EmptyViewModel>() {
    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private val GALLERY_RED_CODE: Int = 1000
//    private var type1: Int = -1
//    private var type2: Int = -1
//    private var type3: Int = -1
//    private var type4: Int = -1
    private var numberQuiz: Int = 0
    private var questionIndex = 0
    private var checkVisibleComplete: Boolean = false
    private var level: Int = -1

    var listQuestionCreate: ArrayList<CreateQuestion?> = arrayListOf()

    var listNumberQuestion: ArrayList<Int> = arrayListOf()

    var listTextViewAnswer: ArrayList<CheckBox> = arrayListOf()

    var listResults: ArrayList<Int> = arrayListOf()

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutOnClickCreate.setOnTouchListener { _, _ -> true }

        /** Insert checkbox in array **/
        listTextViewAnswer.add(isAnswer1)
        listTextViewAnswer.add(isAnswer2)
        listTextViewAnswer.add(isAnswer3)
        listTextViewAnswer.add(isAnswer4)

        /** Create List RecyclerView question **/
        numberQuiz = activity?.intent!!.getIntExtra("number_question", -1)
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recycleListNumber.layoutManager = linearLayoutManager

        /** Create array results **/
        for(i in 0 until 4){
            listResults.add(-1)
        }

        /** Create element for array **/
        for(i in 0 until numberQuiz){
            listNumberQuestion.add(-2)
            listQuestionCreate.add(null)
        }

        saveListPositive(listNumberQuestion,PreferenceKey.LIST_CREATE_NUMBER_QUESTION)

        positiveQuestionAdapter = PositiveQuestionAdapter(numberQuiz, requireActivity())
        positiveQuestionAdapter.onClickItem = {
            questionIndex = it
        }
        recycleListNumber.adapter = positiveQuestionAdapter

        initUi()
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun initUi() {
        val time = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TIME_EXAM, 0)
        txtTime.text = "$time phút"

        selectAnswer()

        positiveQuestionAdapter.onClickItem = {
            val strLevel: String = txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập độ khó cho câu hỏi",Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                doEmptyText()
                questionIndex = it

                setTextView()
            }
        }

        /** On Click **/
        nextQuestionCreate.setOnClickListener {
            val strLevel: String = txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập độ khó cho câu hỏi",Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                doEmptyText()

                visibleCompleteExam()

                if (questionIndex < numberQuiz - 1) {
                    questionIndex++
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        backQuestionCreate.setOnClickListener {
            val strLevel: String = txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập độ khó cho câu hỏi",Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                if (questionIndex > 0) {
                    questionIndex--
                    doEmptyText()
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        completeCreateTest.setOnClickListener {
            saveExam()
            val bundle = Bundle()
            bundle.putInt("numberQuiz",numberQuiz)
            val fragmentReviewCreateExam = FragmentReviewCreateExam()
            val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fm?.add(R.id.changeIdCreateExam, fragmentReviewCreateExam)
                ?.addToBackStack(null)?.commit()
            fragmentReviewCreateExam.arguments = bundle
        }

        backCreateTest.setOnClickListener {
            activity?.finish()
        }

        addCoverImageCreateTest.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        menuCreateTestAct.setOnClickListener {
            showMenuCreate(
                menuCreateTestAct,
                R.layout.popup_create_test_activity,
                0,
                -30,
                Gravity.BOTTOM
            )
        }

        createLevel.setOnClickListener {
            showMenuLevel(createLevel, 0, -30, Gravity.BOTTOM)
        }

        setText()
    }

    private fun clearFocusTextView() {
        questionCreate.clearFocus()
        answerCreate1.clearFocus()
        answerCreate2.clearFocus()
        answerCreate3.clearFocus()
        answerCreate4.clearFocus()
    }

    private fun setTextView() {
        val listQuestion = getListQuestion(PreferenceKey.LIST_CREATE_QUESTION_EXAM)
        if(listQuestion[questionIndex]?.question_title != null){
            questionCreate.setText(listQuestion[questionIndex]?.question_title)
            answerCreate1.setText(listQuestion[questionIndex]?.answer_list?.get(0)?.content)
            answerCreate2.setText(listQuestion[questionIndex]?.answer_list?.get(1)?.content)
            answerCreate3.setText(listQuestion[questionIndex]?.answer_list?.get(2)?.content)
            answerCreate4.setText(listQuestion[questionIndex]?.answer_list?.get(3)?.content)
            val answerList = listQuestion[questionIndex]?.answer_list
            for(i in 0 until answerList?.size!!){
                if(answerList[i]?.type == 1){
                    listTextViewAnswer[i].isChecked = true
                }
            }
        }
    }

    private fun visibleCompleteExam() {
        getListPositive(PreferenceKey.LIST_CREATE_NUMBER_QUESTION).let {
            for (i in 0 until it.size) {
                if (it[i] != 0) {
                    checkVisibleComplete = false
                    break
                } else {
                    checkVisibleComplete = true
                }
            }
            if(checkVisibleComplete){
                completeCreateTest.visibility = View.VISIBLE
            }else{
                completeCreateTest.visibility = View.INVISIBLE
            }
        }
    }

    private fun saveExam() {
        scrollCreateExam.post {
            scrollCreateExam.fling(0)
            scrollCreateExam.smoothScrollTo(0, 0)
        }
        val question: String = questionCreate.text.toString()
        val answer1: String = answerCreate1.text.toString()
        val answer2: String = answerCreate2.text.toString()
        val answer3: String = answerCreate3.text.toString()
        val answer4: String = answerCreate4.text.toString()

        val answers = mutableListOf<CreateAnswer?>()

        if(question.isEmpty()){
            listNumberQuestion[questionIndex] = -1
            saveListPositive(listNumberQuestion,PreferenceKey.LIST_CREATE_NUMBER_QUESTION)
            positiveQuestionAdapter.notifyDataSetChanged()
        }else{
            listNumberQuestion[questionIndex] = 0
            saveListPositive(listNumberQuestion,PreferenceKey.LIST_CREATE_NUMBER_QUESTION)
            positiveQuestionAdapter.notifyDataSetChanged()
        }

        answers.add(CreateAnswer(answer1, "", 1, "", listResults[0]))
        answers.add(CreateAnswer(answer2, "", 2, "", listResults[1]))
        answers.add(CreateAnswer(answer3, "", 3, "", listResults[2]))
        answers.add(CreateAnswer(answer4, "", 4, "", listResults[3]))

        val listNumber = getListPositive(PreferenceKey.LIST_CREATE_NUMBER_QUESTION)
        if(listNumber[questionIndex] == 0){
            listQuestionCreate[questionIndex] = CreateQuestion(answers, "", "", level, questionIndex + 1, question)
        }else{
            listQuestionCreate.add(
                CreateQuestion(answers, "", "", level, questionIndex + 1, question)
            )
        }
        saveListQuestion(listQuestionCreate, PreferenceKey.LIST_CREATE_QUESTION_EXAM)
    }

    private fun saveListPositive(list: ArrayList<Int>, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    private fun getListPositive(key: String?): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveListQuestion(list: ArrayList<CreateQuestion?>, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    private fun getListQuestion(key: String?): ArrayList<CreateQuestion?> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<CreateQuestion?>>() {}.type
        return gson.fromJson(json, type)
    }

    internal fun visibleComplete(visible: Boolean) {
        if (visible) {
            layoutOnClickCreate.visibility = View.GONE
        } else {
            layoutOnClickCreate.visibility = View.VISIBLE
        }
    }

    private fun doEmptyText() {
        questionCreate.setText("")
        answerCreate1.setText("")
        answerCreate2.setText("")
        answerCreate3.setText("")
        answerCreate4.setText("")
        setUnSelectAnswer()
    }

    private fun selectAnswer() {
        isAnswer1.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                isAnswer1.isChecked = true
                listResults[0] = 1
            }
        }
        isAnswer2.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                isAnswer2.isChecked = true
                listResults[1] = 1
            }
        }
        isAnswer3.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                isAnswer3.isChecked = true
                listResults[2] = 1
            }
        }
        isAnswer4.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                isAnswer4.isChecked = true
                listResults[3] = 1
            }
        }
    }

    private fun setUnSelectAnswer() {
        isAnswer1.isChecked = false
        listResults[0] = 0
        isAnswer2.isChecked = false
        listResults[1] = 0
        isAnswer3.isChecked = false
        listResults[2] = 0
        isAnswer4.isChecked = false
        listResults[3] = 0
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCreateTest.typeface = semibold
        txtSelectImageCt.typeface = semibold
        txtAddQuestion.typeface = semibold
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                imageCoverCreateTest.setImageURI(data?.data)
            }
        }
    }

    private fun showMenuCreate(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    private fun showMenuLevel(anchor: View, x: Int, y: Int, position: Int){
        val popUpView: View = View.inflate(requireActivity(), R.layout.popup_level, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)

        /** init view **/
        val easy: TextView = popUpView.findViewById(R.id.levelEasy)
        val medium: TextView = popUpView.findViewById(R.id.levelMedium)
        val hard: TextView = popUpView.findViewById(R.id.levelHard)

        /** On click view **/
        easy.setOnClickListener {
            level = 0
            txtLevel.text = "Dễ"
            popupWindow.dismiss()
        }

        medium.setOnClickListener {
            level = 1
            txtLevel.text = "Trung bình"
            popupWindow.dismiss()
        }

        hard.setOnClickListener {
            level = 2
            txtLevel.text = "Khó"
            popupWindow.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_exam, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}