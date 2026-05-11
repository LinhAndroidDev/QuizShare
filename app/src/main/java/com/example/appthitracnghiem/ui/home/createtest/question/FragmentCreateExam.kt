package com.example.appthitracnghiem.ui.home.createtest.question

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentCreateExamBinding
import com.example.appthitracnghiem.model.CreateAnswer
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.ui.home.createtest.review.FragmentReviewCreateExam
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.content.edit

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentCreateExam : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentCreateExamBinding? = null
    private val binding get() = _binding!!

    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private var numberQuiz: Int = 0
    private var questionIndex = 0
    private var checkVisibleComplete: Boolean = false
    private var level: Int = -1

    private var listQuestionCreate: ArrayList<CreateQuestion?> = arrayListOf()

    private var listNumberQuestion: ArrayList<Int> = arrayListOf()

    private var listTextViewAnswer: ArrayList<CheckBox> = arrayListOf()

    private var listResults: ArrayList<Int> = arrayListOf()

    companion object {
        private const val GALLERY_RED_CODE = 1000
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutOnClickCreate.setOnTouchListener { _, _ -> true }

        /** Insert checkbox in array **/
        listTextViewAnswer.add(binding.isAnswer1)
        listTextViewAnswer.add(binding.isAnswer2)
        listTextViewAnswer.add(binding.isAnswer3)
        listTextViewAnswer.add(binding.isAnswer4)

        /** Create List RecyclerView question **/
        numberQuiz = activity?.intent!!.getIntExtra("number_question", -1)
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleListNumber.layoutManager = linearLayoutManager

        /** Create array results **/
        repeat(4) {
            listResults.add(-1)
        }

        /** Create element for array **/
        repeat(numberQuiz) {
            listNumberQuestion.add(-2)
            listQuestionCreate.add(null)
        }

        saveListPositive(listNumberQuestion)

        positiveQuestionAdapter = PositiveQuestionAdapter(numberQuiz, requireActivity())
        positiveQuestionAdapter.onClickItem = {
            questionIndex = it
        }
        binding.recycleListNumber.adapter = positiveQuestionAdapter

        initUi()
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun initUi() {
        val time = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TIME_EXAM, 0)
        binding.txtTime.text = getString(R.string.format_minutes_suffix, time)

        selectAnswer()

        positiveQuestionAdapter.onClickItem = {
            val strLevel: String = binding.txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                doEmptyText()
                questionIndex = it

                setTextView()
            }
        }

        /** On Click **/
        binding.nextQuestionCreate.setOnClickListener {
            val strLevel: String = binding.txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                doEmptyText()

                visibleCompleteExam()

                if (questionIndex < numberQuiz - 1) {
                    questionIndex++
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                binding.recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        binding.backQuestionCreate.setOnClickListener {
            val strLevel: String = binding.txtLevel.text.toString()

            if(strLevel.isEmpty()){
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT).show()
            }else{
                clearFocusTextView()
                saveExam()
                if (questionIndex > 0) {
                    questionIndex--
                    doEmptyText()
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                binding.recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        binding.completeCreateTest.setOnClickListener {
            saveExam()
            val bundle = Bundle()
            bundle.putInt("numberQuiz",numberQuiz)
            val fragmentReviewCreateExam = FragmentReviewCreateExam()
            val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fm?.add(R.id.changeIdCreateExam, fragmentReviewCreateExam)
                ?.addToBackStack(null)?.commit()
            fragmentReviewCreateExam.arguments = bundle
        }

        binding.backCreateTest.setOnClickListener {
            activity?.finish()
        }

        binding.addCoverImageCreateTest.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        binding.menuCreateTestAct.setOnClickListener {
            binding.menuCreateTestAct.showMenuCreate(R.layout.popup_create_test_activity)
        }

        binding.createLevel.setOnClickListener {
            binding.createLevel.showMenuLevel()
        }
    }

    private fun clearFocusTextView() {
        binding.questionCreate.clearFocus()
        binding.answerCreate1.clearFocus()
        binding.answerCreate2.clearFocus()
        binding.answerCreate3.clearFocus()
        binding.answerCreate4.clearFocus()
    }

    private fun setTextView() {
        val listQuestion = getListQuestion()
        if(listQuestion[questionIndex]?.question_title != null){
            binding.questionCreate.setText(listQuestion[questionIndex]?.question_title)
            binding.answerCreate1.setText(listQuestion[questionIndex]?.answer_list?.get(0)?.content)
            binding.answerCreate2.setText(listQuestion[questionIndex]?.answer_list?.get(1)?.content)
            binding.answerCreate3.setText(listQuestion[questionIndex]?.answer_list?.get(2)?.content)
            binding.answerCreate4.setText(listQuestion[questionIndex]?.answer_list?.get(3)?.content)
            val answerList = listQuestion[questionIndex]?.answer_list
            for(i in 0 until answerList?.size!!){
                if(answerList[i]?.type == 1){
                    listTextViewAnswer[i].isChecked = true
                }
            }
        }
    }

    private fun visibleCompleteExam() {
        getListPositive().let {
            for (i in 0 until it.size) {
                if (it[i] != 0) {
                    checkVisibleComplete = false
                    break
                } else {
                    checkVisibleComplete = true
                }
            }
            if(checkVisibleComplete){
                binding.completeCreateTest.visibility = View.VISIBLE
            }else{
                binding.completeCreateTest.visibility = View.INVISIBLE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun saveExam() {
        binding.scrollCreateExam.post {
            binding.scrollCreateExam.fling(0)
            binding.scrollCreateExam.smoothScrollTo(0, 0)
        }
        val question: String = binding.questionCreate.text.toString()
        val answer1: String = binding.answerCreate1.text.toString()
        val answer2: String = binding.answerCreate2.text.toString()
        val answer3: String = binding.answerCreate3.text.toString()
        val answer4: String = binding.answerCreate4.text.toString()

        val answers = mutableListOf<CreateAnswer?>()

        if(question.isEmpty()){
            listNumberQuestion[questionIndex] = -1
            saveListPositive(listNumberQuestion)
            positiveQuestionAdapter.notifyDataSetChanged()
        }else{
            listNumberQuestion[questionIndex] = 0
            saveListPositive(listNumberQuestion)
            positiveQuestionAdapter.notifyDataSetChanged()
        }

        answers.add(CreateAnswer(answer1, "", 1, "", listResults[0]))
        answers.add(CreateAnswer(answer2, "", 2, "", listResults[1]))
        answers.add(CreateAnswer(answer3, "", 3, "", listResults[2]))
        answers.add(CreateAnswer(answer4, "", 4, "", listResults[3]))

        val listNumber = getListPositive()
        if(listNumber[questionIndex] == 0){
            listQuestionCreate[questionIndex] = CreateQuestion(answers, "", "", level, questionIndex + 1, question)
        }else{
            listQuestionCreate.add(
                CreateQuestion(answers, "", "", level, questionIndex + 1, question)
            )
        }
        saveListQuestion(listQuestionCreate)
    }

    private fun saveListPositive(list: ArrayList<Int>) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        prefs.edit {
            val gson = Gson()
            val json: String = gson.toJson(list)
            putString(PreferenceKey.LIST_CREATE_NUMBER_QUESTION, json)
        }
    }

    private fun getListPositive(): ArrayList<Int> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(PreferenceKey.LIST_CREATE_NUMBER_QUESTION, null)
        val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveListQuestion(list: ArrayList<CreateQuestion?>) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        prefs.edit {
            val gson = Gson()
            val json: String = gson.toJson(list)
            putString(PreferenceKey.LIST_CREATE_QUESTION_EXAM, json)
        }
    }

    private fun getListQuestion(): ArrayList<CreateQuestion?> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(PreferenceKey.LIST_CREATE_QUESTION_EXAM, null)
        val type: Type = object : TypeToken<ArrayList<CreateQuestion?>>() {}.type
        return gson.fromJson(json, type)
    }

    internal fun visibleComplete(visible: Boolean) {
        if (visible) {
            binding.layoutOnClickCreate.visibility = View.GONE
        } else {
            binding.layoutOnClickCreate.visibility = View.VISIBLE
        }
    }

    private fun doEmptyText() {
        binding.questionCreate.setText("")
        binding.answerCreate1.setText("")
        binding.answerCreate2.setText("")
        binding.answerCreate3.setText("")
        binding.answerCreate4.setText("")
        setUnSelectAnswer()
    }

    private fun selectAnswer() {
        binding.isAnswer1.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                binding.isAnswer1.isChecked = true
                listResults[0] = 1
            }
        }
        binding.isAnswer2.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                binding.isAnswer2.isChecked = true
                listResults[1] = 1
            }
        }
        binding.isAnswer3.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                binding.isAnswer3.isChecked = true
                listResults[2] = 1
            }
        }
        binding.isAnswer4.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                binding.isAnswer4.isChecked = true
                listResults[3] = 1
            }
        }
    }

    private fun setUnSelectAnswer() {
        binding.isAnswer1.isChecked = false
        listResults[0] = 0
        binding.isAnswer2.isChecked = false
        listResults[1] = 0
        binding.isAnswer3.isChecked = false
        listResults[2] = 0
        binding.isAnswer4.isChecked = false
        listResults[3] = 0
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                binding.imageCoverCreateTest.setImageURI(data?.data)
            }
        }
    }

    private fun View.showMenuCreate(layout: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(this, 0, -30, Gravity.BOTTOM)
    }

    @SuppressLint("SetTextI18n")
    private fun View.showMenuLevel(){
        val popUpView: View = View.inflate(requireActivity(), R.layout.popup_level, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(this, 0, -30, Gravity.BOTTOM)

        /** init view **/
        val easy: TextView = popUpView.findViewById(R.id.levelEasy)
        val medium: TextView = popUpView.findViewById(R.id.levelMedium)
        val hard: TextView = popUpView.findViewById(R.id.levelHard)

        /** On click view **/
        easy.setOnClickListener {
            level = 0
            binding.txtLevel.text = getString(R.string.txtLevelEasy)
            popupWindow.dismiss()
        }

        medium.setOnClickListener {
            level = 1
            binding.txtLevel.text = getString(R.string.txtMedium)
            popupWindow.dismiss()
        }

        hard.setOnClickListener {
            level = 2
            binding.txtLevel.text = getString(R.string.txtLevelHard)
            popupWindow.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}