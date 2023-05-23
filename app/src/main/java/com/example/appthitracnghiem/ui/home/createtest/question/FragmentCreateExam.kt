package com.example.appthitracnghiem.ui.home.createtest.question

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.CreateAnswer
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.CreateExamViewModel
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_exam.*

@Suppress("DEPRECATION")
class FragmentCreateExam : BaseFragment<CreateExamViewModel>() {
    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private val GALLERY_RED_CODE: Int = 1000
    private var isComplete: Boolean = false
    private var type1: Int = -1
    private var type2: Int = -1
    private var type3: Int = -1
    private var type4: Int = -1
    private var sort: Int = 1

    var onClickContinue: ((Boolean) -> Unit)? = null

    companion object{
        var listQuestionCreate: ArrayList<CreateQuestion?> = arrayListOf()
        var listAnswerCreate: ArrayList<CreateAnswer?> = arrayListOf()
        var listPositiveQuestion: ArrayList<PositiveQuestion> = arrayListOf()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Create List RecyclerView question **/
        val numberQuiz: Int = activity?.intent!!.getIntExtra("number_question",-1)
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recycleListNumber.layoutManager = linearLayoutManager

        listPositiveQuestion.clear()
        for (i in 0 until numberQuiz) {
            if(i == 0){
                listPositiveQuestion.add(PositiveQuestion(i + 1,true))
            }else{
                listPositiveQuestion.add(PositiveQuestion(i + 1,false))
            }
        }
        positiveQuestionAdapter = PositiveQuestionAdapter(listPositiveQuestion, requireActivity())
        positiveQuestionAdapter.getPositiveQuestion = {
            if(it == (listPositiveQuestion.size-1)){
                txtContinue.text = "Hoàn thành"
                isComplete = true
            }else{
                txtContinue.text = "Tiếp theo"
                isComplete = false
            }
        }
        recycleListNumber.adapter = positiveQuestionAdapter

        val time = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TIME_EXAM, 0)
        txtTime.text = "$time phút"


        for(i in 0 until 4){
            listAnswerCreate.add(null)
        }

        val number: Int = activity?.intent!!.getIntExtra("number_question",-1)
        for(i in 0 until number){
            listQuestionCreate.add(null)
        }

        initUi()
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
                val fm : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                val fragmentManageExam = FragmentManageExam()
                fm?.replace(R.id.changeIdCreateExam, fragmentManageExam)?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    private fun initUi() {

        selectAnswer()

        /** On Click **/
        continueCreateTest.setOnClickListener {
            listPositiveQuestion[sort].isSelect = true
            sort++

//            val question: String = questionCreate.text.toString()
//            val answer1: String = answerCreate1.text.toString()
//            val answer2: String = answerCreate2.text.toString()
//            val answer3: String = answerCreate3.text.toString()
//            val answer4: String = answerCreate4.text.toString()

//            if(question.isEmpty() || answer1.isEmpty() && answer2.isEmpty() && answer3.isEmpty() && answer4.isEmpty()){
//                Toast.makeText(requireActivity(), "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
//            }else{
//                if(answer1.isNotEmpty()){
//                    listAnswerCreate[0] = CreateAnswer(answer1,"",1,"",type1)
//                }
//                if(answer2.isNotEmpty()){
//                    listAnswerCreate[1] = CreateAnswer(answer2,"",2,"",type2)
//                }
//                if(answer3.isNotEmpty()){
//                    listAnswerCreate[2] = CreateAnswer(answer3,"",3,"",type3)
//                }
//                if(answer4.isNotEmpty()){
//                    listAnswerCreate[3] = CreateAnswer(answer4,"",4,"",type4)
//                }
//
//                listQuestionCreate[sort-1] = CreateQuestion(listAnswerCreate, "", "", 1, sort, question)
//                sort++
//
//                if(isComplete){
//                    val header = viewModel.mPreferenceUtil.defaultPref()
//                        .getString(PreferenceKey.AUTHORIZATION,"").toString()
//                    val userId = viewModel.mPreferenceUtil.defaultPref()
//                        .getInt(PreferenceKey.USER_ID, 0)
//                    val title = viewModel.mPreferenceUtil.defaultPref()
//                        .getString(PreferenceKey.CREATE_TITLE,"").toString()
//                    val time = viewModel.mPreferenceUtil.defaultPref()
//                        .getInt(PreferenceKey.TIME_EXAM, 0)
//                    val number: Int = activity?.intent!!.getIntExtra("number_question",-1)
//                    val status = viewModel.mPreferenceUtil.defaultPref()
//                        .getInt(PreferenceKey.CREATE_STATUS, 0)
//                    val t = listQuestionCreate
//                    val requestCreateExam = RequestCreateExam(
//                        listQuestionCreate, userId, 1, title, time, number, status
//                    )
//                    viewModel.createExam(header, requestCreateExam)
//                }
//                doEmptyText()
//            }
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

        setText()
    }

    internal fun visibleComplete(visible: Boolean){
        if(visible){
            continueCreateTest.visibility = View.GONE
        }else{
            continueCreateTest.visibility = View.VISIBLE
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

    private fun selectAnswer(){
        isAnswer1.setOnCheckedChangeListener { _, b ->
            if(b){
                setUnSelectAnswer()
                isAnswer1.isChecked = true
                type1 = 1
            }
        }
        isAnswer2.setOnCheckedChangeListener { _, b ->
            if(b){
                setUnSelectAnswer()
                isAnswer2.isChecked = true
                type2 = 1
            }
        }
        isAnswer3.setOnCheckedChangeListener { _, b ->
            if(b){
                setUnSelectAnswer()
                isAnswer3.isChecked = true
                type3 = 1
            }
        }
        isAnswer4.setOnCheckedChangeListener { _, b ->
            if(b){
                setUnSelectAnswer()
                isAnswer4.isChecked = true
                type4 = 1
            }
        }
    }

    private fun setUnSelectAnswer(){
        isAnswer1.isChecked = false
        type1 = 0
        isAnswer2.isChecked = false
        type2 = 0
        isAnswer3.isChecked = false
        type3 = 0
        isAnswer4.isChecked = false
        type4 = 0
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
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