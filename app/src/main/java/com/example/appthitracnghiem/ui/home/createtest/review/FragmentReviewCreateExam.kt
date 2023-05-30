package com.example.appthitracnghiem.ui.home.createtest.review

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.SharedPreferences
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.utils.Const
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.UriConvertFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_create_exam.*
import kotlinx.android.synthetic.main.fragment_review_create_exam.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.lang.reflect.Type

class FragmentReviewCreateExam : BaseFragment<CreateExamViewModel>() {
    lateinit var positionReviewAdapter: PositionReviewAdapter
    var questionIndex: Int = 0
    var numberQuiz: Int = 0
    var listTextViewAnswer: ArrayList<TextView> = arrayListOf()
    var time: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                val fragmentManageExam = FragmentManageExam()
                val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                fm?.add(R.id.changeIdCreateExam, fragmentManageExam)
                    ?.addToBackStack(null)?.commit()
            }
        }

        viewModel.uploadSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireActivity(), "Tải ảnh lên thành công", Toast.LENGTH_SHORT).show()
            }
        }
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

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        time = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TIME_EXAM, 0)
        txtTimeReview.text = "$time phút"

        setTextExam(questionIndex)

        positionReviewAdapter.onClickItem = {
            questionIndex = it
            setTextExam(questionIndex)
        }

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
            val header = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION, "").toString()
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val title = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.CREATE_TITLE, "").toString()
            val number: Int = activity?.intent!!.getIntExtra("number_question", -1)
            val status = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.CREATE_STATUS, 0)
            val subjectId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.CREATE_SUBJECT_ID, -1)
            val listQuestionCreate = getListQuestion(PreferenceKey.LIST_CREATE_QUESTION_EXAM)

            val requestCreateExam = RequestCreateExam(
                listQuestionCreate, userId, subjectId, title, time, number, status
            )
            viewModel.createExam(header, requestCreateExam)

            val strImage = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.CREATE_URI_IMAGE_SUBJECT, "").toString()
            val uriImage: Uri = Uri.parse(strImage)
            val strPath: String = UriConvertFile.getFileFromUri(requireActivity(),uriImage).toString()
            val file = File(strPath)
            val requestBodyImage: RequestBody =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(),file)
            val multipartBodyImage: MultipartBody.Part =
                MultipartBody.Part.createFormData(Const.file,file.name,requestBodyImage)
            val requestBodyId: RequestBody =
                userId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val folder = "exam"
            val requestBodyFolder: RequestBody =
                folder.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val fileName = "23471341347.jpg"
            val requestBodyFileName: RequestBody =
                fileName.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            viewModel.postUploadFile(header,requestBodyId,multipartBodyImage,requestBodyFolder,requestBodyFileName)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_create_exam, container, false)
    }
}