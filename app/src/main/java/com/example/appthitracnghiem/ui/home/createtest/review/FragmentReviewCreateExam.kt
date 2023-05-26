package com.example.appthitracnghiem.ui.home.createtest.review

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_create_exam.*
import kotlinx.android.synthetic.main.fragment_review_create_exam.*
import java.lang.reflect.Type

class FragmentReviewCreateExam : BaseFragment<CreateExamViewModel>() {
    lateinit var positionReviewAdapter: PositionReviewAdapter
    var questionIndex: Int = 0
    var numberQuiz: Int = 0
    var listTextViewAnswer: ArrayList<TextView> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        if (isComplete) {
//            val header = viewModel.mPreferenceUtil.defaultPref()
//                .getString(PreferenceKey.AUTHORIZATION, "").toString()
//            val userId = viewModel.mPreferenceUtil.defaultPref()
//                .getInt(PreferenceKey.USER_ID, 0)
//            val title = viewModel.mPreferenceUtil.defaultPref()
//                .getString(PreferenceKey.CREATE_TITLE, "").toString()
//            val time = viewModel.mPreferenceUtil.defaultPref()
//                .getInt(PreferenceKey.TIME_EXAM, 0)
//            val number: Int = activity?.intent!!.getIntExtra("number_question", -1)
//            val status = viewModel.mPreferenceUtil.defaultPref()
//                .getInt(PreferenceKey.CREATE_STATUS, 0)
//            val requestCreateExam = RequestCreateExam(
//                listQuestionCreate, userId, 1, title, time, number, status
//            )
//            viewModel.createExam(header, requestCreateExam)
//        }

        /** Insert textview answer in array **/
        listTextViewAnswer.add(answerReview1)
        listTextViewAnswer.add(answerReview2)
        listTextViewAnswer.add(answerReview3)
        listTextViewAnswer.add(answerReview4)

        numberQuiz = requireArguments().getInt("numberQuiz")
        positionReviewAdapter = PositionReviewAdapter(numberQuiz, requireActivity())
        val linear = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        recycleListNumberReview.layoutManager = linear
        recycleListNumberReview.adapter = positionReviewAdapter

        initUi()
    }

    private fun setTextExam(index: Int){
        val listQuestion = getListQuestion(PreferenceKey.LIST_CREATE_QUESTION_EXAM)
        txtQuestionReview.text = listQuestion[index]?.question_title
        answerReview1.text = listQuestion[index]?.answer_list?.get(0)?.content
        answerReview2.text = listQuestion[index]?.answer_list?.get(1)?.content
        answerReview3.text = listQuestion[index]?.answer_list?.get(2)?.content
        answerReview4.text = listQuestion[index]?.answer_list?.get(3)?.content
        for(i in 0 until listQuestion[index]?.answer_list!!.size){
            if(listQuestion[index]?.answer_list?.get(i)?.type == 1){
                listTextViewAnswer[i].setBackgroundResource(R.drawable.boder_answer_create)
            }else{
                listTextViewAnswer[i].setBackgroundResource(R.drawable.boder_setting_new_password)
            }
        }
    }

    private fun initUi() {

        setTextExam(questionIndex)

        nextQuestionReview.setOnClickListener {
            if (questionIndex < numberQuiz - 1) {
                questionIndex++
                positionReviewAdapter.setSelectedIndex(questionIndex)
            }
            recycleListNumberReview.scrollToPosition(questionIndex)
            setTextExam(questionIndex)
        }

        backQuestionReview.setOnClickListener {
            if (questionIndex > 0) {
                questionIndex--
                positionReviewAdapter.setSelectedIndex(questionIndex)
            }
            recycleListNumberReview.scrollToPosition(questionIndex)
            setTextExam(questionIndex)
        }

        doneExamReview.setOnClickListener {
            val fragmentManageExam = FragmentManageExam()
            val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fm?.add(R.id.changeIdCreateExam, fragmentManageExam)
                ?.addToBackStack(null)?.commit()
        }

        backReview.setOnClickListener {
            activity?.onBackPressed()
        }

        setText()
    }

    private fun getListQuestion(key: String?): ArrayList<CreateQuestion?> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<CreateQuestion?>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtSeeAgain.typeface = semibold
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                val fragmentManageExam = FragmentManageExam()
                fm?.replace(R.id.changeIdCreateExam, fragmentManageExam)?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_create_exam, container, false)
    }
}